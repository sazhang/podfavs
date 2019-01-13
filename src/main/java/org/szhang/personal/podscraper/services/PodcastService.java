package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.repositories.PodcastRepository;

import java.util.*;

/**
 * Handle interactions with the {@PodcastRepository}.
 */
@Service
@Transactional
public class PodcastService {

  private final PodcastRepository podcastRepository;

  @Autowired
  public PodcastService(PodcastRepository podcastRepository) {
    this.podcastRepository = podcastRepository;
  }

  public Collection<Podcast> getRecsGivenPodcastName(String name) {
    return podcastRepository.getRecsGivenPodcastName(name);
  }

  public Collection<Podcast> getPodcastsGivenWordsOr(List<String> keywords) {
    return podcastRepository.getPodcastsGivenWordsOr(keywords);
  }

  public Collection<Podcast> getPodcastsGivenWordsAnd(List<String> keywords) {
    return podcastRepository.getPodcastsGivenWordsAnd(keywords);
  }
}
