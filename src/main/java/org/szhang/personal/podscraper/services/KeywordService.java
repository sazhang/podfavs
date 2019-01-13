package org.szhang.personal.podscraper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szhang.personal.podscraper.repositories.KeywordRepository;

/**
 * Handle interactions with the {@KeywordRepository}.
 */
@Service
@Transactional
public class KeywordService {

  private final KeywordRepository keywordRepository;

  @Autowired
  public KeywordService(KeywordRepository keywordRepository) {
    this.keywordRepository = keywordRepository;
  }
}
