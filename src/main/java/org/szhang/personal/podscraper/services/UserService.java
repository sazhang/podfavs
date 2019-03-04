package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.repositories.UserRepository;

import java.util.Collection;

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
}
