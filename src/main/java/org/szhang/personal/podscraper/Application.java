package org.szhang.personal.podscraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.util.StitcherScraper;
import org.szhang.personal.podscraper.repositories.PodcastRepository;

import java.util.Map;

@SpringBootApplication
public class Application {

  private final static Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  /*@Bean
  CommandLineRunner demo(PodcastRepository podcastRepository) {
    return args -> {

      podcastRepository.deleteAll();
      StitcherScraper scraper = new StitcherScraper();
      Map<String, Podcast> allPodcasts = scraper.runScraper();
      log.info("Successfully parsed podcasts...");
      log.info("Before linking up with Neo4j...");
      for (Map.Entry<String, Podcast> podcast : allPodcasts.entrySet()) {
        log.info("\t" + podcast.getKey()); // log title
        podcastRepository.save(podcast.getValue());
      }
    };
  }*/


}
