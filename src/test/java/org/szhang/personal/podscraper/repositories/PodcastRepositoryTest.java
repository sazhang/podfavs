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
    String name = "The Dropout";
    Collection<Podcast> podcasts = podcastRepository.getRecsGivenPodcastName(name);
    assertEquals(12, podcasts.size());
  }

  /**
   * Test the getPodcastsGivenWordsOr method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastsGivenWordsOr() {
    List<String> keywords = new ArrayList<>(Arrays.asList("startup", "money"));
    Collection<Podcast> podcasts = podcastRepository.getPodcastsGivenWordsOr(keywords);
    assertEquals(3, podcasts.size());
  }

  /**
   * Test the getPodcastsGivenWordsAnd method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastsGivenWordsAnd() {
    List<String> keywords = new ArrayList<>(Arrays.asList("tech"));
    Collection<Podcast> podcasts = podcastRepository.getPodcastsGivenWordsAnd(keywords);
    assertEquals(6, podcasts.size());
  }

  /**
   * Test the getPodcastByName method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastByName() {
    String name = "The Dropout";
    Podcast result = podcastRepository.getPodcastByName(name);
    List<Keyword> keywords = result.getKeywords();
    List<String> words = keywords.stream().map(Keyword::getWord).collect(Collectors.toList());
    assertEquals(14, words.size());
    List<Category> categories = result.getCategories();
    List<String> cats = categories.stream().map(Category::getCategory).collect(Collectors.toList());
    assertEquals(1, cats.size());
  }

  /**
   * Test the getPodcastById method in {@PodcastRepository}
   */
  @Test
  public void testGetPodcastById() {
    Podcast result = podcastRepository.getPodcastByID(Long.valueOf(18932));
    List<Keyword> keywords = result.getKeywords();
    assertEquals(15, keywords.size());
    List<Category> categories = result.getCategories();
    assertEquals(1, categories.size());
  }

  /**
   * Test both getRecsBasedOnSearch and getPodcastsByIds methods in {@PodcastRepository}
   */
  @Test
  public void testGetRecsBasedOnSearch() {
    List<String> words = new ArrayList<>(Arrays.asList("how i built", "politics"));
    Iterable<Map<String,Object>> results = podcastRepository.getRecsBasedOnSearch(words);
    List<Long> ids = new ArrayList<>();
    for (Map<String,Object> map : results) {
      ids.add((Long) map.get("ids"));
    }
    assertEquals(5, ids.size());
    Collection<Podcast> podcasts = podcastRepository.getPodcastsByIds(ids);
    assertEquals(5, podcasts.size());
    Podcast aPodcast = podcasts.iterator().next();
    assertEquals(11, aPodcast.getKeywords().size());
    assertEquals(1, aPodcast.getCategories().size());
  }
}
