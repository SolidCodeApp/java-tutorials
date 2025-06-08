package com.solid.dao.interfaces;

import java.util.List;
import java.util.Optional;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * Generic Data Access Object (DAO) interface defining basic CRUD operations.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity identifier
 *
 *             Author: Samano CASTRE
 *             Date: 2025-06-08
 */
public interface IDao<T, ID> {

    /**
     * Finds an entity by its primary key.
     *
     * @param entityManager the EntityManager to use
     * @param id            the identifier of the entity
     * @return an Optional containing the found entity, or empty if not found
     * @throws DaoException if a persistence error occurs
     */
    public Optional<T> findById(EntityManager entityManager, ID id) throws DaoException;

    /**
     * Retrieves all entities of type T from the database.
     *
     * @param entityManager the EntityManager to use
     * @return a list of all entities
     * @throws DaoException if a persistence error occurs
     */
    public List<T> findAll(EntityManager entityManager) throws DaoException;

    /**
     * Persists a new entity or updates an existing one in the database.
     *
     * @param entityManager the EntityManager to use
     * @param entity        the entity to save
     * @return an Optional containing the saved entity
     * @throws DaoException if a persistence error occurs
     */
    public Optional<T> save(EntityManager entityManager, T entity) throws DaoException;

    /**
     * Deletes an entity from the database.
     *
     * @param entityManager the EntityManager to use
     * @param entity        the entity to delete
     * @throws DaoException if a persistence error occurs
     */
    public void delete(EntityManager entityManager, T entity) throws DaoException;

    /**
     * Finds all entities where a specific field matches a given value.
     *
     * @param entityManager the EntityManager to use
     * @param fieldName     the name of the field to filter by
     * @param value         the value to match
     * @return a list of matching entities
     * @throws DaoException if a persistence error occurs
     */
    public List<T> findBy(EntityManager entityManager, String fieldName, Object value) throws DaoException;

    /**
     * Finds one entity where a specific field matches a given value.
     *
     * @param entityManager the EntityManager to use
     * @param fieldName     the name of the field to filter by
     * @param value         the value to match
     * @return an Optional containing the found entity, or empty if not found
     * @throws DaoException if a persistence error occurs
     */
    public Optional<T> findOneBy(EntityManager entityManager, String fieldName, Object value) throws DaoException;

    /**
     * Finds a unique entity where a specific field matches a given value.
     * Expects at most one result; throws exception if multiple results are found.
     *
     * @param entityManager the EntityManager to use
     * @param fieldName     the name of the field to filter by
     * @param value         the value to match
     * @return an Optional containing the unique entity, or empty if not found
     * @throws DaoException if a persistence error occurs or multiple results are
     *                      found
     */
    public Optional<T> findUniqueBy(EntityManager entityManager, String fieldName, Object value) throws DaoException;
}
