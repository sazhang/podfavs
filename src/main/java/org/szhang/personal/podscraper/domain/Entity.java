package org.szhang.personal.podscraper.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Every entity requires an id.
 */
@NodeEntity
public abstract class Entity {

  @Id
  @GeneratedValue
  private Long id;

  public Long getId() {
    return id;
  }
}