package org.szhang.personal.podscraper.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Category;
import org.szhang.personal.podscraper.domain.Keyword;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

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

  private String michael;
  
  @Before
  public void initialize() {
    michael = "michael@dundermifflin.co";
  }

  @Test
  public void testGetPodcastById() {
    Podcast result = podcastRepository.getPodcastByID(18852L);
    List<Keyword> keywords = result.getKeywords();
    assertEquals(5, keywords.size());
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
  public void testGetFeaturedPodcasts() {
    Collection<Podcast> savedPodcasts = podcastRepository.getFeaturedPodcasts();
    assertEquals(6, savedPodcasts.size());
    Podcast aPodcast = savedPodcasts.iterator().next();
    assertTrue(aPodcast.getKeywords().size() > 0);
    assertEquals(1, aPodcast.getCategories().size());
  }

  @Test
  public void testCreateUser() {
    assertNull(userRepository.findByEmail(michael));
    userRepository.save(new User(michael));
    assertNotNull(userRepository.findByEmail(michael));
  }

  @Test
  public void testSaveThenUnsavePodcast() {
    long podcastId = 18837L;
    // save a podcast
    assertEquals(0, userRepository.getMySavedPodcasts(michael).size());
    userRepository.saveAPodcast(podcastId, michael);
    assertEquals(1, userRepository.getMySavedPodcasts(michael).size());

    // thanks to neo4j's MERGE call, there is only one relationship between michael and the podcast
    userRepository.saveAPodcast(podcastId, michael);
    assertEquals(1, userRepository.getMySavedPodcasts(michael).size());
    // unsave a podcast
    userRepository.unsaveAPodcast(podcastId, michael);
    assertEquals(0, userRepository.getMySavedPodcasts(michael).size());
  }

  @Test
  public void testSaveThenUnsaveManyPodcasts() {
    List<Long> podcastIds = new ArrayList<>(Arrays.asList(18837L, 18861L));
    // save podcasts
    assertEquals(0, userRepository.getMySavedPodcasts(michael).size());
    userRepository.saveAllPodcasts(podcastIds, michael);
    assertEquals(2, userRepository.getMySavedPodcasts(michael).size());
    // unsave podcasts
    userRepository.unsaveAllPodcasts(podcastIds, michael);
    assertEquals(0, userRepository.getMySavedPodcasts(michael).size());
  }
}
