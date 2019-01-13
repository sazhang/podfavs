package org.szhang.personal.podscraper.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Keyword associated with the podcast.
 */
@NodeEntity
public class Keyword extends Entity {

  private String word;

  @JsonManagedReference
  @Relationship(type = "TAGGED_AS", direction = Relationship.INCOMING)
  private List<Podcast> podcasts;

  /**
   * Construct a new unique keyword with the given word.
   *
   * @param word  keyword
   */
  public Keyword(String word) {
    this.word = word;
  }

  private Keyword() {
    // empty constructor required by neo4j api
  }

  /**
   * Add a podcast to this keyword.
   *
   * @param podcast   podcast
   */
  public void addPodcast(Podcast podcast) {
    this.podcasts.add(podcast);
  }

  public String getWord() {
    return word;
  }

  public List<Podcast> getPodcasts() {
    return podcasts;
  }

  @Override
  public String toString() {
    return "Keyword{" +
        "word='" + word + '\'' +
        ", podcasts=" + podcasts +
        '}';
  }
}
