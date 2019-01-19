package org.szhang.personal.podscraper.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Category;
import org.szhang.personal.podscraper.domain.Keyword;
import org.szhang.personal.podscraper.domain.Podcast;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PodcastRepositoryTest {

  @Autowired
  private PodcastRepository podcastRepository;

  /**
   * Test the getRecsGivenPodcastName method in {@PodcastRepository}
   */
  @Test
  public void testGetRecsGivenPodcastName() {
    String name = "NBC Meet the Press";
    Collection<Podcast> podcasts = podcastRepository.getRecsGivenPodcastName(name);
    assertEquals(9, podcasts.size());
  }

  /**
   * Test the getPodcastsGivenWordsOr method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastsGivenWordsOr() {
    List<String> keywords = new ArrayList<>(Arrays.asList("political", "tech"));
    Collection<Podcast> podcasts = podcastRepository.getPodcastsGivenWordsOr(keywords);
    assertEquals(11, podcasts.size());
  }

  /**
   * Test the getPodcastsGivenWordsAnd method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastsGivenWordsAnd() {
    List<String> keywords = new ArrayList<>(Arrays.asList("political"));
    Collection<Podcast> podcasts = podcastRepository.getPodcastsGivenWordsAnd(keywords);
    assertEquals(4, podcasts.size());
  }

  /**
   * Test the getPodcastByName method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastByName() {
    String name = "Page Seven";
    Podcast result = podcastRepository.getPodcastByName(name);
    List<Keyword> keywords = result.getKeywords();
    List<String> words = keywords.stream().map(Keyword::getWord).collect(Collectors.toList());
    assertEquals(6, words.size());
    List<Category> categories = result.getCategories();
    List<String> cats = categories.stream().map(Category::getCategory).collect(Collectors.toList());
    assertEquals(1, cats.size());
  }

  /**
   * Test the getPodcastById method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastById() {
    Podcast result = podcastRepository.getPodcastByID(Long.valueOf(18543));
    List<Keyword> keywords = result.getKeywords();
    assertEquals(5, keywords.size());
    List<Category> categories = result.getCategories();
    assertEquals(1, categories.size());
  }

  @Test
  public void testGetRecsBasedOnSearch() {
    List<String> words = new ArrayList<>(Arrays.asList("tech", "Page Seven"));
    Collection<Podcast> podcasts = podcastRepository.getRecsBasedOnSearch(words);
    assertEquals(9, podcasts.size());
  }

  @Test
  public void testgetBackupRecs() {
    List<String> words = new ArrayList<>(Arrays.asList("how i built"));
    Collection<Podcast> firstTry = podcastRepository.getRecsBasedOnSearch(words);
    assertEquals(0, firstTry.size());
    Collection<Podcast> backup = podcastRepository.getBackupRecs(words);
    assertEquals(4, backup.size());
  }
}
