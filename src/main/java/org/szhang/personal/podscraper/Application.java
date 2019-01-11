package org.szhang.personal.podscraper;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*import org.szhang.personal.podscraper.events.EventPublisher;
import org.szhang.personal.podscraper.events.PostSaveEvent;
import org.szhang.personal.podscraper.events.PreDeleteEvent;
import org.szhang.personal.podscraper.events.PreSaveEvent;*/

@SpringBootApplication
@EntityScan("org.szhang.personal.podscraper.domain")
public class Application {

//  private final static Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  /*@Bean
  public EventPublisher eventPublisher() {
    return new EventPublisher();
  }


  @EventListener
  public void onPreSaveEvent(PreSaveEvent event) {
    Object entity = event.getSource();
    System.out.println("Before save of: " + entity);
  }

  @EventListener
  public void onPostSaveEvent(PostSaveEvent event) {
    Object entity = event.getSource();
    System.out.println("After save of: " + entity);
  }

  @EventListener
  public void onPreDeleteEvent(PreDeleteEvent event) {
    Object entity = event.getSource();
    System.out.println("Before delete of: " + entity);
  }*/
}
