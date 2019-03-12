package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;
import org.szhang.personal.podscraper.services.PodcastService;
import org.szhang.personal.podscraper.services.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Handle requests related to podcasts.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class PodcastController {

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

  @GetMapping("/mypodcasts")
  public Collection<Podcast> getMySavedPodcasts(JwtAuthenticationToken authentication) {
    //OAuth2Authentication authentication = (OAuth2Authentication) principal;
    //Map<String, Object> details = principal.getAttributes();
    //Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
    Map<String, Object> details = authentication.getTokenAttributes();
    System.out.println("THE details " + details);
    String userId = details.get("uid").toString();
    System.out.println("THE userId " + userId);
    String email = details.get("sub").toString();
    System.out.println("THE EMAIL " + email);
    User user = getUserFromDb(userId, email);
    return user.getSavedPodcasts();
  }

  @PostMapping("/save/podcast/{podId}/user{userId}")
  public void saveAPodcast(Long podId, String userId) {
    userService.saveAPodcast(podId, userId);
  }

  @DeleteMapping("/unsave/podcast/{podId}/user{userId}")
  public void unsaveAPodcast(Long podId, String userId) {
    userService.unsaveAPodcast(podId, userId);
  }

  @PostMapping("/savelist")
  public void saveAllPodcasts(List<Long> podIds, String userId) {
    userService.saveAllPodcasts(podIds, userId);
  }

  @DeleteMapping("/unsavelist")
  public void unsaveAllPodcasts(List<Long> podIds, String userId) {
    userService.unsaveAllPodcasts(podIds, userId);
  }

  /**
   * Check if user exists in database, else create one.
   *
   * @param userId user id given by Okta
   * @param email  user's email address
   * @return user
   */
  private User getUserFromDb(String userId, String email) {
    User user = userService.findByUserId(userId);
    if (user == null) {
      return userService.createUser(userId, email);
    } else {
      return user;
    }
  }
}
