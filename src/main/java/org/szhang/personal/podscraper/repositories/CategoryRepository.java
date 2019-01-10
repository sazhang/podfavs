package org.szhang.personal.podscraper.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.szhang.personal.podscraper.domain.Category;

/**
 * Support queries of Category nodes.
 */
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends Neo4jRepository<Category, Long> {

  Category findByCategory(@Param("category") String category);
}
