package com.solid.dao.abstracts;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.dao.interfaces.IDao;
import com.solid.enums.EErrorCode;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;

/**
 * Generic DAO implementation for CRUD operations using JPA EntityManager.
 * Provides basic data access methods for entities of type T with identifier ID.
 * 
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class AbstractDaoImpl<T, ID> implements IDao<T, ID> {

    private Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);

    private final Class<T> entityClass;

    /**
     * Constructor initializing DAO for a specific entity class.
     *
     * @param entityClass the entity class this DAO manages
     */
    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Finds an entity by its identifier.
     *
     * @param entityManager the JPA EntityManager to use
     * @param id            the identifier of the entity to find
     * @return Optional containing the found entity or empty if not found
     * @throws DaoException if an error occurs during retrieval
     */
    @Override
    public Optional<T> findById(EntityManager entityManager, ID id) throws DaoException {
        try {
            logger.info("Attempting to fetch entity of type '{}' ...", entityClass.getSimpleName());
            logger.debug("Attempting to fetch entity of type '{}' using id '{}'",
                    entityClass.getSimpleName(),
                    id);

            return Optional.ofNullable(entityManager.find(entityClass, id));
        } catch (IllegalArgumentException exception) {
            String errMessage = String.format("Failed to fetch entity of type '%s'.", entityClass.getSimpleName());

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Retrieves all entities of the managed type.
     *
     * @param entityManager the JPA EntityManager to use
     * @return list of all entities
     * @throws DaoException if an error occurs during retrieval
     */
    @Override
    public List<T> findAll(EntityManager entityManager) throws DaoException {
        try {
            logger.info("Attempting to fetch all entities of type {} ...",
                    entityClass.getSimpleName());

            String query = "SELECT e FROM " + entityClass.getSimpleName() + " e ORDER BY e.id";

            logger.debug("JPQL Query: {}", query);

            return entityManager.createQuery(query, entityClass).getResultList();

        } catch (IllegalArgumentException exception) {
            String errMessage = String.format("Failed to fetch all entities of type '%s'.",
                    entityClass.getSimpleName());

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Saves or updates the given entity.
     *
     * @param entityManager the JPA EntityManager to use
     * @param entity        the entity to save or update
     * @return Optional containing the persisted entity
     * @throws DaoException if an error occurs during persistence
     */
    @Override
    public Optional<T> save(EntityManager entityManager, T entity) throws DaoException {
        try {
            logger.info("Attempting to save or update entity of type '{}'...",
                    entityClass.getSimpleName());

            return Optional.ofNullable(entityManager.merge(entity));

        } catch (IllegalArgumentException exception) {
            String errMessage = String.format("Failed to save or update entity of type '%s'.",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);

        } catch (TransactionRequiredException exception) {
            String errMessage = String.format("Failed to save or update entity of type '%s'. A transaction is required",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Deletes the given entity.
     *
     * @param entityManager the JPA EntityManager to use
     * @param entity        the entity to delete
     * @throws DaoException if an error occurs during deletion
     */
    @Override
    public void delete(EntityManager entityManager, T entity) throws DaoException {
        try {
            logger.info("Attempting to delete entity of type '{}'", entityClass.getSimpleName());

            if (!entityManager.contains(entity)) {
                String errMessage = String.format("Entity of type '%s' is detached and cannot be deleted directly." +
                        "Please persist it first if needed.", entityClass.getSimpleName());
                logger.error(errMessage);
                throw new DaoException(EErrorCode.DATA_ERROR, errMessage);

            }

            entityManager.remove(entity);
        } catch (IllegalArgumentException exception) {
            String errMessage = String.format("Failed to delete entity of type '%s'." +
                    "Invalid argument provided.",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);

        } catch (TransactionRequiredException exception) {
            String errMessage = String.format("Failed to delete entity of type '%s'. A transaction is required",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Finds entities filtered by a specified field and value.
     *
     * @param entityManager the JPA EntityManager to use
     * @param fieldName     the field name to filter by
     * @param value         the value of the field
     * @return list of matching entities
     * @throws DaoException if an error occurs during query or if field is invalid
     */
    @Override
    public List<T> findBy(EntityManager entityManager, String fieldName, Object value) throws DaoException {
        try {
            logger.info("Attempting to fetch list of entities of type {}," +
                    " filtered by specifiec field and value.", entityClass.getSimpleName());
            this.ensureFieldExists(fieldName);

            return this.buildSelectByFieldQuery(entityManager, fieldName, value).getResultList();

        } catch (Exception exception) {
            String errMessage = String.format("Failed to fetch list of entities of type '%s' using field '%s'",
                    entityClass.getSimpleName(), fieldName);
            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Builds a JPQL TypedQuery to select entities filtered by a specific field and
     * value.
     *
     * @param entityManager the JPA EntityManager to create the query
     * @param fieldName     the name of the entity field to filter by
     * @param value         the value of the field to match
     * @return a TypedQuery configured with the filter parameter
     * @throws DaoException if query creation fails
     */
    private TypedQuery<T> buildSelectByFieldQuery(EntityManager entityManager, String fieldName, Object value) {
        try {

            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName +
                    " = :value ORDER BY e.id";

            logger.debug("JPQL Query: {}\nParam [value = {}]", jpql, value);

            TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
            query.setParameter("value", value);
            return query;

        } catch (Exception exception) {
            String errMessage = String.format("Failed to build query for fiend '%s' in entity '%s'.",
                    fieldName,
                    entityClass.getSimpleName());

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Ensures the entity class contains the specified field.
     *
     * @param fieldName the field name to verify
     * @return the field name if exists
     * @throws IllegalArgumentException if the field does not exist
     */
    protected String ensureFieldExists(String fieldName) {
        try {
            return entityClass.getDeclaredField(fieldName).getName();
        } catch (NoSuchFieldException exception) {

            logger.debug("Field name '{}' not found in entity class '{}'",
                    fieldName,
                    entityClass.getSimpleName());
            throw new IllegalArgumentException("Invalid field name : " + fieldName, exception);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Failed to ensure that field '" + fieldName + "' exists",
                    exception);
        }

    }

    /**
     * Finds one entity filtered by a specified field and value.
     *
     * @param entityManager the JPA EntityManager to use
     * @param fieldName     the field name to filter by
     * @param value         the value of the field
     * @return Optional containing the found entity or empty if none found
     * @throws DaoException if an error occurs during query or if field is invalid
     */
    @Override
    public Optional<T> findOneBy(EntityManager entityManager, String fieldName, Object value) throws DaoException {
        try {
            logger.info("Attempting to fetch one entity of type {} using field {}",
                    entityClass.getSimpleName(),
                    fieldName);

            ensureFieldExists(fieldName);

            TypedQuery<T> query = this.buildSelectByFieldQuery(entityManager, fieldName, value);

            query.setMaxResults(1);

            List<T> list = query.getResultList();

            return list.stream().findFirst();
        } catch (Exception exception) {
            String errMessage = String.format("Failed to fetch one entity of type '%s'" +
                    " using field '%s'.",
                    entityClass.getSimpleName(),
                    fieldName);

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Finds a unique entity filtered by a specified field and value.
     * Throws an exception if more than one entity matches.
     *
     * @param entityManager the JPA EntityManager to use
     * @param fieldName     the field name to filter by
     * @param value         the value of the field
     * @return Optional containing the unique found entity or empty if none found
     * @throws DaoException if an error occurs or if uniqueness is violated
     */
    @Override
    public Optional<T> findUniqueBy(EntityManager entityManager, String fieldName, Object value) throws DaoException {
        try {
            logger.info("Attempting to fetch a unique entity of type {} using field {}",
                    entityClass.getSimpleName(),
                    fieldName);

            this.ensureFieldExists(fieldName);

            TypedQuery<T> query = this.buildSelectByFieldQuery(entityManager, fieldName, value);
            query.setMaxResults(2);

            List<T> list = query.getResultList();

            this.checkUniqueness(list);

            return list.stream().findFirst();

        } catch (Exception exception) {
            String errMessage = String.format("Failed a unique entity of type '%s'.",
                    entityClass.getSimpleName());

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Checks if the list contains more than one entity and throws
     * an exception if uniqueness is violated.
     *
     * @param list the list of entities to check
     * @throws RuntimeException if multiple entities are found
     */
    protected void checkUniqueness(List<T> list) {
        if (list.size() > 1) {
            String errMessage = String.format("Field values are not unique in entity '%s'." +
                    " Duplicate entries found.",
                    entityClass.getSimpleName());

            logger.error(errMessage);

            logger.debug("Non-uniqueness detected in entity {}. Duplicated entries {}",
                    entityClass.getSimpleName(),
                    list);

            throw new RuntimeException(errMessage);
        }
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

}