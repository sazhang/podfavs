package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.services.PodcastService;
import org.szhang.personal.podscraper.services.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Handle requests related to podcasts.
 */
@RestController
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
  public Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "id") Long id) {
    return userService.getMySavedPodcasts(id);
  }

  @PostMapping("/save/podcast/{podId}/user{userId}")
  public void saveAPodcast(Long podId, Long userId) {
    userService.saveAPodcast(podId, userId);
  }

  @DeleteMapping("/unsave/podcast/{podId}/user{userId}")
  public void unsaveAPodcast(Long podId, Long userId) {
    userService.unsaveAPodcast(podId, userId);
  }

  @PostMapping("/savelist")
  public void saveAllPodcasts(List<Long> podIds, Long userId) {
    userService.saveAllPodcasts(podIds, userId);
  }

  @DeleteMapping("/unsavelist")
  public void unsaveAllPodcasts(List<Long> podIds, Long userId) {
    userService.unsaveAllPodcasts(podIds, userId);
  }
}
