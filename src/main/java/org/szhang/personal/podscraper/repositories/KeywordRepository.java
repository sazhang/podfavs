package org.szhang.personal.podscraper.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.szhang.personal.podscraper.domain.Keyword;
import org.springframework.data.repository.CrudRepository;

/**
 * Support queries of Keyword nodes.
 */
@RepositoryRestResource(collectionResourceRel = "keywords", path = "keywords")
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

  Keyword findByKeyword(@Param("word") String keyword);

}
