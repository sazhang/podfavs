package org.szhang.personal.podscraper.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.szhang.personal.podscraper.domain.Host;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Support queries of Host nodes.
 */
@RepositoryRestResource(collectionResourceRel = "hosts", path = "hosts")
public interface HostRepository extends CrudRepository<Host, Long> {

  Host findByHost(@Param("name") String hostName);
//  Host findByPodcasts(List<String> podcasts);
}
