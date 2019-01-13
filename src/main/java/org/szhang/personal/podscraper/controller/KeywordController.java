package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szhang.personal.podscraper.services.KeywordService;

/**
 * Handle user requests.
 */
@RestController
@RequestMapping("/suggest")
public class KeywordController {

  private final KeywordService keywordService;

  @Autowired
  public KeywordController(KeywordService keywordService) {
    this.keywordService = keywordService;
  }
}
