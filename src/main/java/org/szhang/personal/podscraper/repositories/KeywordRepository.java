package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.szhang.personal.podscraper.domain.Keyword;
import org.springframework.data.repository.CrudRepository;

/**
 * Support queries of Keyword nodes.
 */
@Repository
public interface KeywordRepository extends Neo4jRepository<Keyword, Long> {

  Keyword findByWord(String word);

}
