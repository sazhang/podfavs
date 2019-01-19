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
  public Page<Podcast> findSomePodcasts() {
    Pageable pageable = PageRequest.of(0, 15);
    return podcastRepository.findAll(pageable, 0);
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getRecsBasedOnSearch(List<String> words) {
    Collection<Podcast> podcasts = podcastRepository.getRecsBasedOnSearch(words);
    if (podcasts.isEmpty()) {
      podcasts = podcastRepository.getBackupRecs(words);
    }
    int counter = 0;
    while (counter < words.size()) {
      String word = words.get(counter);
      podcasts.removeIf(p -> p.getName().equals(word));
      counter++;
    }
    return podcasts;
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getBackupRecs(List<String> words) {
    return podcastRepository.getBackupRecs(words);
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getRecsGivenPodcastName(String name) {
    return podcastRepository.getRecsGivenPodcastName(name);
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getPodcastsGivenWordsOr(List<String> keywords) {
    return podcastRepository.getPodcastsGivenWordsOr(keywords);
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getPodcastsGivenWordsAnd(List<String> keywords) {
    return podcastRepository.getPodcastsGivenWordsAnd(keywords);
  }

  @Transactional(readOnly = true)
  public Podcast getPodcastByName(String name) {
    return podcastRepository.getPodcastByName(name);
  }

  @Transactional(readOnly = true)
  public Podcast getPodcastByID(Long id) {
    return podcastRepository.getPodcastByID(id);
  }
}
