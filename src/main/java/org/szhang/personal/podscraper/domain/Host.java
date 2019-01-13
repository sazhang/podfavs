package org.szhang.personal.podscraper.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Host of the podcast.
 */
@NodeEntity
public class Host extends Entity {

  private String name;

  @JsonBackReference
  @Relationship(type = "HOSTS")
  private List<Podcast> podcasts;

  /**
   * Construct a new host with given characteristics.
   *
   * @param name      host name
   */
  public Host(String name) {
    this.name = name;
    this.podcasts = new ArrayList<>();
  }

  private Host() {
    // empty constructor required by neo4j api
  }

  /**
   * Add a podcasts to this host's list of podcasts.
   *
   * @param podcast   podcasts
   */
  public void addPodcast(Podcast podcast) {
    this.podcasts.add(podcast);
  }

  public String getName() {
    return name;
  }

  public List<Podcast> getPodcasts() {
    return podcasts;
  }

  @Override
  public String toString() {
    return "Host{" +
        "name='" + name + '\'' +
        ", podcasts=" + podcasts +
        '}';
  }
}
