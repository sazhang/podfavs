package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.services.PodcastService;

import java.util.Collection;
import java.util.List;

/**
 * Handle user requests.
 */
@RestController
@RequestMapping("/suggest")
public class PodcastController {

  private final PodcastService podcastService;

  @Autowired
  public PodcastController(PodcastService podcastService) {
    this.podcastService = podcastService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Collection<Podcast> getRecsGivenPodcastName(@RequestParam(value = "name") String name) {
    return podcastService.getRecsGivenPodcastName(name);
  }

  @RequestMapping(value = "/or", method = RequestMethod.GET)
  public Collection<Podcast> getPodcastsGivenWordsOr(@RequestParam(value = "keywords") List<String> keywords) {
    return podcastService.getPodcastsGivenWordsOr(keywords);
  }

  @RequestMapping(value = "/and", method = RequestMethod.GET)
  public Collection<Podcast> getPodcastsGivenWordsAnd(@RequestParam(value = "keywords") List<String> keywords) {
    return podcastService.getPodcastsGivenWordsAnd(keywords);
  }

  @RequestMapping(value = "/info", method = RequestMethod.GET)
  public Podcast getPodcastByName(@RequestParam(value = "name") String name) {
    return podcastService.getPodcastByName(name);
  }

  @RequestMapping(value = "/id", method = RequestMethod.GET)
  public Podcast getPodcastByID(@RequestParam(value = "id") Long id) {
    return podcastService.getPodcastByID(id);
  }
}
