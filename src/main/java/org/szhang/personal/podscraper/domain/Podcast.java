package org.szhang.personal.podscraper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about a podcast.
 */
@NodeEntity
public class Podcast extends Entity {

  private String name;
  private String description;
  private Double rating;
  private String url;
  private String imageUrl;

  @JsonIgnoreProperties("podcasts")
  @Relationship(type = "BELONGS_TO")
  private List<Category> categories;

  @JsonIgnoreProperties("podcasts")
  @Relationship(type = "TAGGED_AS")
  private List<Keyword> keywords;

  @JsonIgnoreProperties("podcasts")
  @Relationship(type = "HOSTS", direction = Relationship.INCOMING)
  private List<Host> hosts; //TODO: stitcher does not provide these

  @JsonIgnoreProperties("podcasts")
  @Relationship(type = "SAVED", direction = Relationship.INCOMING)
  private List<User> users;

  /**
   * Construct a new podcast with the given characteristics.
   *
   * @param name          podcast name
   * @param descrip       podcast descrip
   * @param rating        rating
   * @param url           link to stitcher profile of the podcast
   * @param imageUrl      link to podcast image
   */
  public Podcast(String name, String descrip, Double rating, String url, String imageUrl) {
    this.name = name;
    this.description = descrip;
    this.rating = rating;
    this.url = url;
    this.imageUrl = imageUrl;
    this.categories = new ArrayList<>();
    this.keywords = new ArrayList<>();
    this.users = new ArrayList<>();
  }

  public Podcast() {
    // empty constructor required by neo4j api
  }

  /**
   * Add a category to this podcast.
   *
   * @param category   category
   */
  public void addACategory(Category category) {
    this.categories.add(category);
  }

  /**
   * Add a keyword to this podcast.
   *
   * @param keyword   keyword
   */
  public void addAKeyword(Keyword keyword) {
    this.keywords.add(keyword);
  }

  /**
   * Add a host to this podcast.
   *
   * @param host   host
   */
  public void addAHost(Host host) {
    this.hosts.add(host);
  }

  /**
   * Getters & setters for podcast characteristics.
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<Keyword> keywords) {
    this.keywords = keywords;
  }

  public List<Host> getHosts() {
    return hosts;
  }

  public void setHosts(List<Host> hosts) {
    this.hosts = hosts;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Podcast{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", rating=" + rating +
        ", url='" + url + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        //", categories=" + categories +
        //", keywords=" + keywords +
        //", hosts=" + hosts +
        '}';
  }
}
