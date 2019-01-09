package podcast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Category describes the topic focus of a podcast.
 */
@NodeEntity
public class Category {

  @Id
  @GeneratedValue
  private Long id;
  private String category;

  @Relationship(type = "IN_CATEGORY", direction = Relationship.INCOMING)
  private List<Podcast> podcasts;

  /**
   * Construct a new category with the given title.
   *
   * @param category    title of this category
   */
  public Category(String category) {
    this.category = category;
  }

  private Category() {
    // empty constructor required by neo4j api
  }

  /**
   * Add a podcast to this category.
   *
   * @param podcast   podcast
   */
  public void addPodcast(Podcast podcast) {
    this.podcasts.add(podcast);
  }

  public String getCategory() {
    return category;
  }

  public List<Podcast> getPodcasts() {
    return podcasts;
  }
}
