package org.szhang.personal.podscraper.service;

/**
 * Defines behavior of CRUD operations.
 */
public interface Service<T> {

  /**
   * List all entities of some type.
   *
   * @return an entity
   */
  Iterable<T> findAll();

  /**
   * Find an entity of some type by id.
   *
   * @param id  unique id
   * @return an entity
   */
  T find(Long id);

  /**
   * Delete the entity of some type with the given id.
   *
   * @param id   unique id
   */
  void delete(Long id);

  /**
   * Create a new entity or update an existing one.
   *
   * @param object  new entity
   * @return new entity
   */
  T createOrUpdate(T object);

}