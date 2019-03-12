package org.szhang.personal.podscraper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User extends Entity {

  @Index(unique=true)
  private String userId; // id is given by Okta

  @Index(unique=true)
  private String email;

  @JsonIgnoreProperties("users")
  @Relationship(type = "SAVED")
  private List<Podcast> savedPodcasts;

  /*@JsonIgnoreProperties("users")
  @Relationship(type = "RATED")
  private List<Podcast> ratedPodcasts;*/

  /**
   * Construct a new user with the given characteristics.
   *
   * @param userId    unique id provided by Okta
   * @param email     user email address
   */
  public User(String userId, String email) {
    this.userId = userId;
    this.email = email;
    this.savedPodcasts = new ArrayList<>();
    //this.ratedPodcasts = new ArrayList<>();
  }

  public User() {
    // empty constructor required by neo4j api
  }

  /**
   * Save a podcast.
   *
   * @param podcast     podcast to save
   */
  public void saveAPodcast(Podcast podcast) {
    savedPodcasts.add(podcast);
  }

  /**
   * Getters & setters for user characteristics.
   */
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Podcast> getSavedPodcasts() {
    return savedPodcasts;
  }

  public void setSavedPodcasts(List<Podcast> savedPodcasts) {
    this.savedPodcasts = savedPodcasts;
  }

  /*public List<Podcast> getRatedPodcasts() {
    return ratedPodcasts;
  }

  public void setRatedPodcasts(List<Podcast> ratedPodcasts) {
    this.ratedPodcasts = ratedPodcasts;
  }*/
}
