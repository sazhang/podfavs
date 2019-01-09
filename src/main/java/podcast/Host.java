package podcast;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Host of the podcast.
 */
@NodeEntity
public class Host {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

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
   * Add a podcast to this host's list of podcasts.
   *
   * @param podcast   podcast
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
}
