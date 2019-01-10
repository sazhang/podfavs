package org.szhang.personal.podscraper;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * This class is used to create instances of Session objects when necessary.
 * Since it's an expensive object to create, set this up once.
 */
public class Neo4jSessionFactory {

  private final static ConfigurationSource props = new ClasspathConfigurationSource("application.properties");
  private final static Configuration configuration = new Configuration.Builder(props).build();
  private final static SessionFactory sessionFactory = new SessionFactory(configuration, "podscraper.domain");
  private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

  public static Neo4jSessionFactory getInstance() {
    return factory;
  }

  private Neo4jSessionFactory() {
  }

  public Session getNeo4jSession() {
    return sessionFactory.openSession();
  }
}