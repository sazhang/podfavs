package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.services.PodcastService;
import org.szhang.personal.podscraper.services.UserService;

import java.util.Collection;
import java.util.List;

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

  @GetMapping("/")
  public Page<Podcast> findSomePodcasts() {
    return podcastService.findSomePodcasts();
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

  /*@RequestMapping(value = "/rec/{name}", method = RequestMethod.GET)
  public Collection<Podcast> getRecsGivenPodcastName(@PathVariable(value = "name") String name) {
    return podcastService.getRecsGivenPodcastName(name);
  }

  @RequestMapping(value = "/or/{keywords}", method = RequestMethod.GET)
  public Collection<Podcast> getPodcastsGivenWordsOr(@PathVariable(value = "keywords") List<String> keywords) {
    return podcastService.getPodcastsGivenWordsOr(keywords);
  }

  @RequestMapping(value = "/and/{keywords}", method = RequestMethod.GET)
  public Collection<Podcast> getPodcastsGivenWordsAnd(@PathVariable(value = "keywords") List<String> keywords) {
    return podcastService.getPodcastsGivenWordsAnd(keywords);
  }

  @RequestMapping(value = "/info/{name}", method = RequestMethod.GET)
  public Podcast getPodcastByName(@PathVariable(value = "name") String name) {
    return podcastService.getPodcastByName(name);
  }*/
}
