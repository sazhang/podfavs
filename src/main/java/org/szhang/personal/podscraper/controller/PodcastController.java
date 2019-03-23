package org.szhang.personal.podscraper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;
import org.szhang.personal.podscraper.services.PodcastService;
import org.szhang.personal.podscraper.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Handle requests related to podcasts.
 */
@RestController
@CrossOrigin(origins = {"http://podscraper-env.zhecf24cjp.us-east-1.elasticbeanstalk.com/", "http://localhost:3000"})
public class PodcastController {

  private final Logger log = LoggerFactory.getLogger(PodcastController.class);

  private final PodcastService podcastService;
  private final UserService userService;

  @Autowired
  public PodcastController(PodcastService podcastService, UserService userService) {
    this.podcastService = podcastService;
    this.userService = userService;
  }

  @GetMapping("/featured")
  public Collection<Podcast> getFeaturedPodcasts() {
    return podcastService.getFeaturedPodcasts();
  }

  @GetMapping("/search/{words}")
  public Collection<Podcast> getRecsBasedOnSearch(@PathVariable(value = "words") List<String> words) {
    return podcastService.getRecsBasedOnSearch(words);
  }

  @GetMapping("/id/{id}")
  public Podcast getPodcastByID(@PathVariable(value = "id") Long id) {
    return podcastService.getPodcastByID(id);
  }

  @GetMapping("/api/mypodcasts")
  public Collection<Podcast> getMySavedPodcasts(Principal principal) {
    String email = principal.getName();
    System.out.println(email);
    log.debug(email);
    User user = getUserFromDb(email);
    Collection<Podcast> queryResult = userService.getMySavedPodcasts(email);
    List<Podcast> podcastNodes = new ArrayList<>();
    for (Podcast podcast : queryResult) {
      podcastNodes.add(podcastService.getPodcastByID(podcast.getId()));
    }
    return podcastNodes;
  }

  @PostMapping("/api/save/{podId}")
  public void saveAPodcast(@PathVariable(value = "podId") Long podId, Principal principal) {
    String email = principal.getName();
    userService.saveAPodcast(podId, email);
  }

  @DeleteMapping("/api/unsave/{podId}")
  public void unsaveAPodcast(@PathVariable(value = "podId") Long podId, Principal principal) {
    String email = principal.getName();
    userService.unsaveAPodcast(podId, email);
  }

  @PostMapping("/api/savelist/{podIds}")
  public void saveAllPodcasts(@PathVariable(value = "podIds") List<Long> podIds, Principal principal) {
    String email = principal.getName();
    userService.saveAllPodcasts(podIds, email);
  }

  @DeleteMapping("/api/unsavelist/{podIds}")
  public void unsaveAllPodcasts(@PathVariable(value = "podIds") List<Long> podIds, Principal principal) {
    String email = principal.getName();
    userService.unsaveAllPodcasts(podIds, email);
  }

  /**
   * Check if user exists in database, else create one.
   *
   * @param email  user's email address
   * @return user
   */
  private User getUserFromDb(String email) {
    /*Map<String, Object> details = authentication.getTokenAttributes();
    String userId = details.get("uid").toString();
    String email = details.get("sub").toString();
    System.out.println(userId);
    System.out.println(email);*/
    User user = userService.findByUserEmail(email);
    if (user == null) {
      System.out.println("USER DOES NOT EXIST SO CREATING ONE");
      return userService.createUser(email);
    } else {
      System.out.println("USER : " + user);
      return user;
    }
  }
}
