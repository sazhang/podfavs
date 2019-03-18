package org.szhang.personal.podscraper.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
//@CrossOrigin(origins = {"http://podscraper-env.zhecf24cjp.us-east-1.elasticbeanstalk.com/", "http://localhost:3000"})
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

  /*@GetMapping("/api/mypodcasts")
  public Collection<Podcast> getMySavedPodcasts(JwtAuthenticationToken authentication) {
    Map<String, Object> details = authentication.getTokenAttributes();
    String email = details.get("sub").toString();
    User user = getUserFromDb(email);
    System.out.println(user.getId());
    System.out.println(userService.getMySavedPodcasts(user.getId()));
    return userService.getMySavedPodcasts(user.getId());
  }*/

  @PostMapping("/api/save/{podId}")
  public void saveAPodcast(@PathVariable(value = "podId") Long podId, @PathVariable(value = "id") Long id) {
    userService.saveAPodcast(podId, id);
  }

  @DeleteMapping("/api/unsave/{podId}")
  public void unsaveAPodcast(@PathVariable(value = "podId") Long podId, @PathVariable(value = "id") Long id) {
    userService.unsaveAPodcast(podId, id);
  }

  @PostMapping("/api/savelist/{podIds}")
  public void saveAllPodcasts(@PathVariable(value = "podIds") List<Long> podIds, @PathVariable(value = "id") Long id) {
    userService.saveAllPodcasts(podIds, id);
  }

  @DeleteMapping("/api/unsavelist/{podIds}")
  public void unsaveAllPodcasts(@PathVariable(value = "podIds") List<Long> podIds, @PathVariable(value = "id") Long id) {
    userService.unsaveAllPodcasts(podIds, id);
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
