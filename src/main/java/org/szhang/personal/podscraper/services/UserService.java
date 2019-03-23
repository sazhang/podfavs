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
    return userRepository.save(new User(email));
  }

  @Transactional
  public User findByUserEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Transactional
  public Collection<Podcast> getMySavedPodcasts(String email) {
    return userRepository.getMySavedPodcasts(email);
  }

  @Transactional
  public void saveAPodcast(Long podId, String email) {
    userRepository.saveAPodcast(podId, email);
  }

  @Transactional
  public void unsaveAPodcast(Long podId, String email) {
    userRepository.unsaveAPodcast(podId, email);
  }

  @Transactional
  public void saveAllPodcasts(List<Long> podIds, String email) {
    userRepository.saveAllPodcasts(podIds, email);
  }

  @Transactional
  public void unsaveAllPodcasts(List<Long> podIds, String email) {
    userRepository.unsaveAllPodcasts(podIds, email);
  }
}
