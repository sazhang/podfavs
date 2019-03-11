package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.repositories.UserRepository;

import java.util.Collection;
import java.util.List;

/**
 * Handle interactions with the {@UserRepository}.
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getMySavedPodcasts(Long id) {
    return userRepository.getMySavedPodcasts(id);
  }

  @Transactional(readOnly = true)
  public Collection<Podcast> getMySavedPodcasts(String name) {
    return userRepository.getMySavedPodcasts(name);
  }

  @Transactional
  public void saveAPodcast(Long podId, Long userId) {
    userRepository.saveAPodcast(podId, userId);
  }

  @Transactional
  public void unsaveAPodcast(Long podId, Long userId) {
    userRepository.unsaveAPodcast(podId, userId);
  }

  @Transactional
  public void saveAllPodcasts(List<Long> podIds, Long userId) {
    userRepository.saveAllPodcasts(podIds, userId);
  }

  @Transactional
  public void unsaveAllPodcasts(List<Long> podIds, Long userId) {
    userRepository.unsaveAllPodcasts(podIds, userId);
  }
}
