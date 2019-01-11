package org.szhang.personal.podscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szhang.personal.podscraper.repositories.CategoryRepository;

@RestController
@RequestMapping(value = "/api/classes")
public class CategoryController {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }
}
