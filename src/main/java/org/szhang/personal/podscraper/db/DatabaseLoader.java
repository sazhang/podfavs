package org.szhang.personal.podscraper.db;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.szhang.personal.podscraper.domain.Category;
import org.szhang.personal.podscraper.domain.Keyword;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;

import java.io.File;
import java.util.*;

/**
 * Scrape Sticher Lists of podcasts and load the data into neo4j.
 */
@Component
@ConditionalOnProperty(name = "DB_RECREATE", havingValue = "true")
public class DatabaseLoader implements CommandLineRunner {

  private Map<String, Keyword> keywords;
  private Map<String, List<String>> podcastsByCategory;
  private Map<String, Podcast> allPodcasts;
  private Map<String, String> podcastToKeywords;
  private WebDriver driver;
  private WebDriverWait wait;

  private final SessionFactory sessionFactory;
  private Session session;

  /**
   * Construct a database loader.
   */
  @Autowired
  public DatabaseLoader(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /**
   * Scrape Stitcher's lists to create podcast objects.
   */
  @Override
  public void run(String... args) throws Exception {
    setUp();
    getPodcastsByCategory();

    session = sessionFactory.openSession();
    //session.purgeDatabase();
    getEachPodcastDetails();
    addTwoTestUsers();

    // according to docs, longer session = more efficient requests to db but memory intensive
    driver.close();
  }

  /**
   * Set up fields.
   */
  private void setUp() {
    keywords = new HashMap<>();
    podcastsByCategory = new HashMap<>();
    allPodcasts = new HashMap<>();
    podcastToKeywords = new HashMap<>();
    File file = new File("C:/Users/Sarah Zhang/chromedriver.exe");
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  /**
   * Get links to all podcasts by category.
   */
  private void getPodcastsByCategory() {
    driver.get("https://www.stitcher.com/stitcher-list/all-podcasts-top-shows");
    // parse nav bar to get links to specific category lists
    List<String> categories = getUrls(driver.findElements(By.xpath("//ul[@id='category-nav']/*")));
    categories.remove(0); // remove "All shows" link
    // access each list link and save all links to podcasts in the list
    for (String category : categories) {
      driver.get(category);
      try {
          String categoryName = driver.findElement(By.id("view-title")).getText();
          List<WebElement> showDetails = wait.until(
              ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='sl-showName']/*")));
          // get top 20 podcasts in each category
          int n = 0;
          List<String> podcastLinks = new ArrayList<>();
          for (WebElement show : showDetails) {
            if (n == 10) {
              break;
            }
            podcastLinks.add(show.getAttribute("href"));
            n++;
          }
        podcastsByCategory.put(categoryName, podcastLinks);
      } catch (Exception te) {
        // some categories do not have top 50 lists so just continue
        continue;
      }
    }
  }

  /**
   * By category, go to each podcast profile and get the show's info.
   */
  private void getEachPodcastDetails() {
    for (Map.Entry<String, List<String>> entry : podcastsByCategory.entrySet()) {
      Category aCategory = new Category(entry.getKey());
      // loop through the current category's list of podcasts
      for (String podcastUrl : entry.getValue()) {
        driver.get(podcastUrl);
        String title;
        double rating;
        String image;
        String kwString;
        String description;
        try {
          title = wait.until(ExpectedConditions.presenceOfElementLocated(
              By.xpath("//meta[@property='og:title']"))).getAttribute("content");
          // if we already created this podcast, then add the current category to it
          if (allPodcasts.containsKey(title)) {
            allPodcasts.get(title).addACategory(aCategory);
            continue;
          }

          // try to grab podcast rating and image
          WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//span[contains(@itemprop, 'ratingValue') and contains(@aria-hidden, 'true')]")));
          rating = Double.parseDouble(ratingElement.getText());
          image = wait.until(ExpectedConditions.presenceOfElementLocated(
              By.xpath("//meta[@property='og:image']"))).getAttribute("content");

          // get keywords and description
          kwString = wait.until(ExpectedConditions.presenceOfElementLocated(
              By.xpath("//meta[@name='keywords']"))).getAttribute("content");
          podcastToKeywords.put(title, kwString);
          description = wait.until(ExpectedConditions.presenceOfElementLocated(
              By.xpath("//p[@id='feed-description-full']"))).getText();
        } catch (Exception e) {
          //unable to grab an attribute so skip
          continue;
        }
        Podcast aPodcast = new Podcast(title, description, rating, podcastUrl, image);
        aPodcast.addACategory(aCategory);
        allPodcasts.put(title, aPodcast);
      }
    }

    // associate keywords with podcasts
    for (Map.Entry<String, String> entry : podcastToKeywords.entrySet()) {
      List<Keyword> keywords = generateKeywords(entry.getValue());
      String currentPodcast = entry.getKey();
      allPodcasts.get(currentPodcast).setKeywords(keywords);
    }

    List<Podcast> completePodcasts = new ArrayList<>(allPodcasts.values());
    session.save(completePodcasts);
    session.clear();
  }

  /**
   * Generate a list of Keywords from the given string of comma delimited keywords.
   *
   * @param kwString  string of keywords
   * @return list of keywords
   */
  private List<Keyword> generateKeywords(String kwString) {
    List<String> splitList = Arrays.asList(kwString.split(","));
    List<Keyword> keywordList = new ArrayList<>();
    for (String word : splitList) {
      String lowerCaseKW = word.toLowerCase();
      // check if we already created the keyword
      if (!keywords.containsKey(lowerCaseKW)) {
        Keyword aKeyword = new Keyword(lowerCaseKW);
        keywords.put(lowerCaseKW, aKeyword);
        keywordList.add(aKeyword);
      } else {
        keywordList.add(keywords.get(lowerCaseKW));
      }
    }
    return keywordList;
  }

  /**
   * Add two users to the database for test use.
   */
  private void addTwoTestUsers() {
    User michael = new User("michaelscott", "michael@dunder.mifflin", "peoplepapercompany");
    User dwight = new User("dwightschrute", "dwight@schrute.farms", "beets4lifejim");
    List<User> testUsers = new ArrayList<>(Arrays.asList(michael, dwight));
    session.save(testUsers);
    session.clear();
  }

  /**
   * Return a list of links from given list of web elements.
   *
   * @param elements  list of web elements
   * @return list of links
   */
  private static List<String> getUrls(List<WebElement> elements) {
    List<String> urls = new ArrayList<>();
    for (WebElement element : elements) {
      urls.add(element.getAttribute("href"));
    }
    return urls;
  }
}
