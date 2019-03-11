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
import static org.junit.Assert.assertTrue;

/**
 * Test methods PodcastController class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PodcastAppTest {

  @Autowired
  private PodcastRepository podcastRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testGetPodcastById() {
    Podcast result = podcastRepository.getPodcastByID(18932L);
    List<Keyword> keywords = result.getKeywords();
    assertEquals(15, keywords.size());
    List<Category> categories = result.getCategories();
    assertEquals(1, categories.size());
  }

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

  @Test
  public void testGetMySavedPodcasts() {
    Collection<Podcast> savedPodcasts = userRepository.getMySavedPodcasts(18645L);
    assertEquals(1, savedPodcasts.size());
    savedPodcasts = userRepository.getMySavedPodcasts("dwightschrute"); // ^ id
    assertEquals(1, savedPodcasts.size());
  }

  @Test
  public void testGetFeaturedPodcasts() {
    Collection<Podcast> savedPodcasts = podcastRepository.getFeaturedPodcasts();
    assertEquals(6, savedPodcasts.size());
    Podcast aPodcast = savedPodcasts.iterator().next();
    assertTrue(aPodcast.getKeywords().size() > 0);
    assertEquals(1, aPodcast.getCategories().size());
  }

  @Test
  public void testSaveThenUnsavePodcast() {
    long michaelId = 19054L;
    long podcastId = 18837L;
    // save a podcast
    assertEquals(2, userRepository.getMySavedPodcasts(michaelId).size());
    userRepository.saveAPodcast(podcastId, michaelId);
    assertEquals(3, userRepository.getMySavedPodcasts(michaelId).size());
    // thanks to neo4j's MERGE call, there is only one relationship between michael and the podcast
    userRepository.saveAPodcast(podcastId, michaelId);
    assertEquals(3, userRepository.getMySavedPodcasts(michaelId).size());
    // unsave a podcast
    userRepository.unsaveAPodcast(podcastId, michaelId);
    assertEquals(2, userRepository.getMySavedPodcasts(michaelId).size());
  }

  @Test
  public void testSaveThenUnsaveManyPodcasts() {
    long michaelId = 19054L;
    List<Long> podcastIds = new ArrayList<>(Arrays.asList(18837L, 18861L));
    // save podcasts
    assertEquals(2, userRepository.getMySavedPodcasts(michaelId).size());
    userRepository.saveAllPodcasts(podcastIds, michaelId);
    assertEquals(4, userRepository.getMySavedPodcasts(michaelId).size());
    // unsave podcasts
    userRepository.unsaveAllPodcasts(podcastIds, michaelId);
    assertEquals(2, userRepository.getMySavedPodcasts(michaelId).size());
  }
}
