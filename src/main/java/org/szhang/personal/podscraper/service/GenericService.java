package org.szhang.personal.podscraper.service;

import org.neo4j.ogm.session.Session;
import org.szhang.personal.podscraper.Neo4jSessionFactory;

/**
 * Support simple CRUD operations for any entity.
 */
abstract class GenericService<T> implements Service<T> {

  private static final int DEPTH_LIST = 0;
  private static final int DEPTH_ENTITY = 1;
  protected Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

  @Override
  public Iterable<T> findAll() {
    return session.loadAll(getEntityType(), DEPTH_LIST);
  }

  @Override
  public T find(Long id) {
    return session.load(getEntityType(), id, DEPTH_ENTITY);
  }

  @Override
  public void delete(Long id) {
    session.delete(session.load(getEntityType(), id));
  }

  @Override
  public abstract T createOrUpdate(T entity);
  /*public Entity createOrUpdate(Entity entity) {
    session.save(entity, DEPTH_ENTITY);
    return find(entity.get());
  }*/

  abstract Class<T> getEntityType();
}