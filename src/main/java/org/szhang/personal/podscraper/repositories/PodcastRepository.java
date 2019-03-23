package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.szhang.personal.podscraper.domain.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Support queries of {@Podcast} nodes & relationships.
 */
@Repository
public interface PodcastRepository extends Neo4jRepository<Podcast, Long> {

  /**
   * Get six random podcasts to feature on the home page.
   *
   * @return podcasts
   */
  @Query("MATCH (p:Podcast) " +
      "WHERE rand() < 0.10 " +
      "WITH p LIMIT 6 " +
      "OPTIONAL MATCH (p:Podcast)-[r]-(b) " +
      "RETURN r, p, b")
  Collection<Podcast> getFeaturedPodcasts();

  /**
   * Recommend podcasts based on user input.
   *
   * @param words   user input that can be podcast name and/or keywords
   * @return list of podcasts
   */
  @Query("UNWIND {words} AS word MATCH (p:Podcast)-[:TAGGED_AS]->(k:Keyword)<-[:TAGGED_AS]-(p2) " +
      "WHERE lower(p.name) CONTAINS lower(word) OR lower(k.word) CONTAINS lower(word) " +
      "OR lower(p.description) CONTAINS lower(word) " +
      "OPTIONAL MATCH (p)-[:BELONGS_TO]->(c:Category)<-[:BELONGS_TO]-(p2) " +
      "WHERE lower(c.category) CONTAINS lower(word) OR lower(p.description) CONTAINS lower(word) " +
      "OR lower(p.name) CONTAINS lower(word) RETURN id(p) as ids, count(p) as freq ORDER BY freq DESC LIMIT 12")
  Iterable<Map<String,Object>> getRecsBasedOnSearch(@Param("words") List<String> words);

  /**
   * Get all podcasts matching the given ids.
   *
   * @param ids   list of podcast ids
   * @return podcasts
   */
  @Query("UNWIND {ids} AS id MATCH (p:Podcast)-[r]-(b) WHERE ID(p) = id RETURN r, p, b")
  Collection<Podcast> getPodcastsByIds(@Param("ids") List<Long> ids);

  /**
   * Get podcast entity by id.
   *
   * @param id  podcast id
   * @return podcast
   */
  @Query("MATCH (p:Podcast)-[r]-(b) WHERE ID(p) = {id} RETURN r, p, b")
  Podcast getPodcastByID(@Param("id") Long id);
}
