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
   * Retrieve user.
   *
   * @param email    user email
   * @return user
   */
  @Query("MATCH (u:User {email: {email} }) RETURN u")
  User findByEmail(@PathVariable(value = "email") String email);

  /**
   * Get the user's list of saved podcasts.
   *
   * @param email    user email
   * @return podcasts
   */
  @Query("MATCH pod=(p:Podcast)<-[:SAVED]-(u:User) WHERE u.email = {email} RETURN pod, nodes(pod), rels(pod)")
  Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "email") String email);

  /**
   * User saves a podcast.
   *
   * @param podId   podcast id
   * @param email    user email
   */
  @Query("MATCH (u:User),(p:Podcast) " +
      "WHERE u.email = {email} AND ID(p) = {podId} " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAPodcast(@Param("podId") Long podId, @PathVariable(value = "email") String email);

  /**
   * User unsaves a podcast.
   *
   * @param podId   podcast id
   * @param email    user email
   */
  @Query("MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE u.email = {email} AND ID(p) = {podId} " +
      "DELETE r")
  void unsaveAPodcast(@Param("podId") Long podId, @PathVariable(value = "email") String email);

  /**
   * User saves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param email    user email
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User),(p:Podcast) " +
      "WHERE u.email = {email} AND ID(p) = podId " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAllPodcasts(@Param("podIds") List<Long> podIds, @PathVariable(value = "email") String email);

  /**
   * User unsaves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param email    user email
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE u.email = {email} AND ID(p) = podId " +
      "DELETE r")
  void unsaveAllPodcasts(@Param("podIds") List<Long> podIds, @PathVariable(value = "email") String email);
}
