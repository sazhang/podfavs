package org.szhang.personal.podscraper.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szhang.personal.podscraper.domain.Podcast;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {
  private static List<Podcast> podcasts = new ArrayList<>();

  static {
    for (int i = 0; i < 10; i++) {
      podcasts.add(new Podcast("hi" + i, "hi", 5.0, "hi", "hi", new ArrayList<>()));
    }
  }

  @GetMapping
  public String getAllPodcasts(Model model) {
    model.addAttribute("podcasts", podcasts);
    return "podcasts";
  }
}
