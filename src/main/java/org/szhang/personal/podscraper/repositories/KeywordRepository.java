package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.szhang.personal.podscraper.domain.Keyword;

/**
 * Support queries of {@Keyword} nodes & relationships.
 */
@Repository
public interface KeywordRepository extends Neo4jRepository<Keyword, Long> {
}
