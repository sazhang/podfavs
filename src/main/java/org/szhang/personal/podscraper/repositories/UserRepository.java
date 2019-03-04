package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;

import java.util.Collection;

/**
 * Support queries of {@User} nodes & relationships.
 */
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

  /**
   * Get the user's list of saved podcasts.
   *
   * @param id   user id
   * @return podcasts
   */
  @Query("MATCH pod=(p:Podcast)-[*]->() WHERE ID(p) = {id} RETURN pod, nodes(pod), rels(pod)")
  Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "id") Long id);
}
