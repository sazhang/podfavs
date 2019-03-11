package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.szhang.personal.podscraper.domain.Podcast;
import org.szhang.personal.podscraper.domain.User;

import java.util.Collection;
import java.util.List;

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
  @Query("MATCH usr=(u:User)-[:SAVED]->(p:Podcast) WHERE ID(u) = {id} RETURN p")
  Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "id") Long id);

  /**
   * Get the user's list of saved podcasts.
   *
   * @param name    username
   * @return podcasts
   */
  @Query("MATCH usr=(u:User)-[:SAVED]->(p:Podcast) WHERE u.username = {name} RETURN p")
  Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "name") String name);

  /**
   * User saves a podcast.
   *
   * @param podId   podcast id
   * @param userId  user id
   */
  @Query("MATCH (u:User),(p:Podcast) " +
      "WHERE ID(u) = {userId} AND ID(p) = {podId} " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAPodcast(@Param("podId") Long podId, @Param("userId") Long userId);

  /**
   * User unsaves a podcast.
   *
   * @param podId   podcast id
   * @param userId  user id
   */
  @Query("MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE ID(u) = {userId} AND ID(p) = {podId} " +
      "DELETE r")
  void unsaveAPodcast(@Param("podId") Long podId, @Param("userId") Long userId);

  /**
   * User saves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param userId  user id
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User),(p:Podcast) " +
      "WHERE ID(u) = {userId} AND ID(p) = podId " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAllPodcasts(@Param("podIds") List<Long> podIds, @Param("userId") Long userId);

  /**
   * User unsaves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param userId  user id
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE ID(u) = {userId} AND ID(p) = podId " +
      "DELETE r")
  void unsaveAllPodcasts(@Param("podIds") List<Long> podIds, @Param("userId") Long userId);
}
