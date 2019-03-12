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
   * Create user.
   *
   * @param userId    user id given by Okta
   * @param email     user's email address
   * @return user
   */
  @Query("CREATE (u:User { userId: {userId}, email: {email} }) RETURN u")
  User createUser(@PathVariable(value = "userId") String userId, @PathVariable(value = "email") String email);

  /**
   * Retrieve user.
   *
   * @param userId    user id
   * @return user
   */
  @Query("MATCH usr=(u:User)-[:SAVED]->(p:Podcast) WHERE u.userId = {userId} RETURN u")
  User findByUserId(@PathVariable(value = "userId") String userId);

  /**
   * Get the user's list of saved podcasts.
   *
   * @param userId    user id
   * @return podcasts
   */
  @Query("MATCH usr=(u:User)-[:SAVED]->(p:Podcast) WHERE u.userId = {userId} RETURN p")
  Collection<Podcast> getMySavedPodcasts(@PathVariable(value = "userId") String userId);

  /**
   * User saves a podcast.
   *
   * @param podId   podcast id
   * @param userId  user id
   */
  @Query("MATCH (u:User),(p:Podcast) " +
      "WHERE u.userId = {userId} AND ID(p) = {podId} " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAPodcast(@Param("podId") Long podId, @Param("userId") String userId);

  /**
   * User unsaves a podcast.
   *
   * @param podId   podcast id
   * @param userId  user id
   */
  @Query("MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE u.userId = {userId} AND ID(p) = {podId} " +
      "DELETE r")
  void unsaveAPodcast(@Param("podId") Long podId, @Param("userId") String userId);

  /**
   * User saves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param userId  user id
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User),(p:Podcast) " +
      "WHERE u.userId = {userId} AND ID(p) = podId " +
      "MERGE (u)-[r:SAVED]->(p)")
  void saveAllPodcasts(@Param("podIds") List<Long> podIds, @Param("userId") String userId);

  /**
   * User unsaves all podcasts in current list.
   *
   * @param podIds   list of podcast ids
   * @param userId  user id
   */
  @Query("UNWIND {podIds} AS podId " +
      "MATCH (u:User)-[r:SAVED]->(p:Podcast) " +
      "WHERE u.userId = {userId} AND ID(p) = podId " +
      "DELETE r")
  void unsaveAllPodcasts(@Param("podIds") List<Long> podIds, @Param("userId") String userId);
}
