package org.szhang.personal.podscraper;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("org.szhang.personal.podscraper.repositories")
@EnableTransactionManagement
public class MyConfiguration {

  private static final String BASE_PACKAGE = "org.szhang.personal.podscraper.domain";

  @Value("${spring.data.neo4j.uri}")
  private String databaseUrl;

  @Value("${spring.data.neo4j.username}")
  private String userName;

  @Value("${spring.data.neo4j.password}")
  private String password;
  /*@Bean
  @ConfigurationProperties("spring.data.neo4j")
  public Neo4jProperties myNeo4jProperties() {
    return new Neo4jProperties();
  }*/

  @Bean
  public SessionFactory sessionFactory() {
    // with domain entity base package(s)
    return new SessionFactory(configuration(), BASE_PACKAGE);
  }

  @Bean
  public org.neo4j.ogm.config.Configuration configuration() {
    org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
        .uri(databaseUrl)
        .credentials(userName, password)
        .build();
    return configuration;
//    return myNeo4jProperties().createConfiguration();
  }

  @Bean
  public Neo4jTransactionManager transactionManager() {
    return new Neo4jTransactionManager(sessionFactory());
  }

}
