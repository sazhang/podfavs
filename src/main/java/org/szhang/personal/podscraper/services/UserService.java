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
  public User createUser(String userId, String email) {
    return userRepository.createUser(userId, email);
  }

  @Transactional
  public User findByUserId(String userId) {
    return userRepository.findByUserId(userId);
  }

  @Transactional
  public void saveAPodcast(Long podId, String userId) {
    userRepository.saveAPodcast(podId, userId);
  }

  @Transactional
  public void unsaveAPodcast(Long podId, String userId) {
    userRepository.unsaveAPodcast(podId, userId);
  }

  @Transactional
  public void saveAllPodcasts(List<Long> podIds, String userId) {
    userRepository.saveAllPodcasts(podIds, userId);
  }

  @Transactional
  public void unsaveAllPodcasts(List<Long> podIds, String userId) {
    userRepository.unsaveAllPodcasts(podIds, userId);
  }
}
