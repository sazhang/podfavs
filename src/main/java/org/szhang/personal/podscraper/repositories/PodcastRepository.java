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
      "OR lower(p.name) CONTAINS lower(word) RETURN id(p) as ids, count(p) as freq ORDER BY freq DESC LIMIT 15")
  Iterable<Map<String,Object>> getRecsBasedOnSearch(@Param("words") List<String> words);

  /**
   * Get all podcasts matching the given ids.
   *
   * @param ids   list of ids
   * @return podcasts
   */
  @Query("UNWIND {ids} AS id MATCH pod=(p:Podcast)-[*]->() WHERE ID(p) = id RETURN pod, nodes(pod), rels(pod)")
  Collection<Podcast> getPodcastsByIds(@Param("ids") List<Long> ids);

  /**
   * Get podcast entity by id.
   *
   * @param id  assigned id
   * @return podcast
   */
  @Query("MATCH pod=(p:Podcast)-[*]->() WHERE ID(p) = {id} RETURN pod, nodes(pod), rels(pod)")
  Podcast getPodcastByID(@Param("id") Long id);

  /**
   * Find other podcasts that share the same keywords as the given podcast.
   *
   * @param name podcast name
   * @return list of podcasts
   */
  @Query("MATCH pod=(p:Podcast {name: {name}})-[:TAGGED_AS]->(k:Keyword)<-[:TAGGED_AS]-(p2) " +
      "RETURN ID(p), ID(p2), pod")
  Collection<Podcast> getRecsGivenPodcastName(@Param("name") String name);

  /**
   * Given a list of keywords, find podcasts featuring at least one of the given words.
   *
   * @param keywords  list of keywords
   * @return podcasts
   */
  @Query("MATCH pod=(p:Podcast)-[:TAGGED_AS]->(k:Keyword) WHERE k.word in {keywords} RETURN ID(p), pod")
  Collection<Podcast> getPodcastsGivenWordsOr(@Param("keywords") List<String> keywords);

  /**
   * Given a list of keywords, find podcasts featuring all the given words.
   *
   * @param keywords  list of keywords
   * @return podcasts
   */
  @Query("WITH {keywords} as words MATCH (k:Keyword) WHERE k.word in words WITH collect(k) as keywords "
      + "WITH head(keywords) as head, tail(keywords) as keywords MATCH (head)<-[:TAGGED_AS]-(p:Podcast) "
      + "WHERE ALL(k in keywords WHERE (k)<-[:TAGGED_AS]-(p)) RETURN ID(p), p")
  Collection<Podcast> getPodcastsGivenWordsAnd(@Param("keywords") List<String> keywords);

  /**
   * Get podcast entity by name.
   *
   * @param name podcast name
   * @return podcast
   */
  @Query("MATCH pod=(p:Podcast {name: {name}})-[*]->() RETURN pod, nodes(pod), rels(pod)")
  Podcast getPodcastByName(@Param("name") String name);
}
