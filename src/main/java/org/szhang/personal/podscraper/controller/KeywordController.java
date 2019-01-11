package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szhang.personal.podscraper.repositories.KeywordRepository;

@RestController
@RequestMapping(value = "/api/classes")
public class KeywordController {
  private KeywordRepository keywordRepository;

  @Autowired
  public KeywordController(KeywordRepository keywordRepository) {
    this.keywordRepository = keywordRepository;
  }
}
