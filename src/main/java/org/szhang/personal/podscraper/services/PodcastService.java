package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.repositories.PodcastRepository;

import java.util.*;

/**
 * Handle interactions with the {@PodcastRepository}.
 */
@Service
public class PodcastService {

  private final PodcastRepository podcastRepository;

  @Autowired
  public PodcastService(PodcastRepository podcastRepository) {
    this.podcastRepository = podcastRepository;
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getFeaturedPodcasts() {
    return podcastRepository.getFeaturedPodcasts();
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getRecsBasedOnSearch(List<String> words) {
    Iterable<Map<String,Object>> results = podcastRepository.getRecsBasedOnSearch(words);
    List<Long> ids = new ArrayList<>();
    for (Map<String,Object> map : results) {
      ids.add((Long) map.get("ids"));
    }
    return podcastRepository.getPodcastsByIds(ids);
  }

  @Transactional(readOnly = true)
  public Podcast getPodcastByID(Long id) {
    return podcastRepository.getPodcastByID(id);
  }
}
