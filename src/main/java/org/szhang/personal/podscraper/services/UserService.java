package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;
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

  @Transactional
  public User createUser(String email) {
    return userRepository.createUser(email);
  }

  @Transactional
  public User findByUserId(String userId) {
    return userRepository.findByUserId(userId);
  }

  @Transactional
  public User findByUserEmail(String email) {
    return userRepository.findByUserEmail(email);
  }

  @Transactional
  public Collection<Podcast> getMySavedPodcasts(Long id) {
    return userRepository.getMySavedPodcasts(id);
  }

  @Transactional
  public void saveAPodcast(Long podId, Long id) {
    userRepository.saveAPodcast(podId, id);
  }

  @Transactional
  public void unsaveAPodcast(Long podId, Long id) {
    userRepository.unsaveAPodcast(podId, id);
  }

  @Transactional
  public void saveAllPodcasts(List<Long> podIds, Long id) {
    userRepository.saveAllPodcasts(podIds, id);
  }

  @Transactional
  public void unsaveAllPodcasts(List<Long> podIds, Long id) {
    userRepository.unsaveAllPodcasts(podIds, id);
  }
}
