package org.szhang.personal.podscraper.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about a podcasts.domain.
 */
@NodeEntity
public class Podcast extends Entity {

  private String name;
  private String description;
  private double rating;
  private String url;
  private String imageUrl;

  @Relationship(type = "IN_CATEGORY")
  private List<Category> categories;

  @Relationship(type = "TAGGED_AS")
  private List<Keyword> keywords;

  @Relationship(type = "HOSTS", direction = Relationship.INCOMING)
  private List<Host> hosts; //TODO: stitcher does not provide these

  /**
   * Construct a new podcasts with the given characteristics.
   *
   * @param name          podcasts name
   * @param category      category this podcast falls under
   * @param descrip       podcasts descrip
   * @param rating        rating
   * @param url           link to stitcher profile of the podcasts
   * @param imageUrl      link to podcasts image
   * @param keywords      stitcher given keywords
   */
  public Podcast(String name, Category category, String descrip, double rating, String url, String imageUrl,
                 List<Keyword> keywords) {
    this.name = name;
    this.description = descrip;
    this.rating = rating;
    this.url = url;
    this.imageUrl = imageUrl;
    this.categories = new ArrayList<>();
    this.categories.add(category);
    this.keywords = keywords;
  }

  private Podcast() {
    // empty constructor required by neo4j api
  }

  /**
   * Add a category to this podcasts.
   *
   * @param category   category
   */
  public void addACategory(Category category) {
    this.categories.add(category);
  }

  /**
   * Add a keyword to this podcasts.
   *
   * @param keyword   keyword
   */
  public void addAKeyword(Keyword keyword) {
    this.keywords.add(keyword);
  }

  /**
   * Add a host to this podcasts.
   *
   * @param host   host
   */
  public void addAHost(Host host) {
    this.hosts.add(host);
  }

  /**
   * Getters & setters for podcasts characteristics.
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

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
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
}
