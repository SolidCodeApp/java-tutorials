package com.solid.dao.abstracts;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solid.dao.interfaces.IDao;
import com.solid.exceptions.DaoException;
import com.solid.exceptions.NonUniqueFieldException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;

/**
 * Generic abstract base class for Data Access Object (DAO) implementations.
 * <p>
 * This class provides a reusable and type-safe foundation for performing common
 * CRUD operations and simple queries on any JPA entity type.
 * It is parameterized by the entity type {@code T} and its identifier type
 * {@code ID},
 * and relies on an injected {@link EntityManager} to interact with the
 * persistence context.
 * <p>
 * All operations are logged at appropriate levels (info, debug, error), and
 * errors are
 * consistently wrapped into {@link DaoException}, ensuring clean separation
 * between
 * data access and business logic.
 * <p>
 * Subclasses should extend this class to gain access to:
 * <ul>
 * <li>Basic CRUD operations ({@code findById}, {@code findAll}, {@code save},
 * {@code delete})</li>
 * <li>Dynamic field-based queries ({@code findBy}, {@code findOneBy},
 * {@code findUniqueBy})</li>
 * <li>Built-in checks for field existence and result uniqueness</li>
 * </ul>
 * 
 * @param <T>  the type of the JPA entity
 * @param <ID> the type of the primary key of the entity
 */
public class AbstractDaoImpl<T, ID> implements IDao<T, ID> {

    private Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);

    private final Class<T> entityClass;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Retrieves an entity by its primary key.
     * Logs the operation at both info and debug levels for traceability.
     * Throws a DaoException in case of invalid arguments or lookup failures.
     *
     * @param entityManager the EntityManager used for the operation
     * @param id            the primary key of the entity to retrieve
     * @return an Optional containing the found entity, or empty if not found
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
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Retrieves all entities of the given type from the database.
     * Constructs and executes a JPQL query dynamically based on the entity class.
     * Logs the query and handles potential issues gracefully.
     *
     * @param entityManager the EntityManager used for the operation
     * @return a List containing all entities found
     * @throws DaoException if the query construction or execution fails
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
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Persists a new entity or updates an existing one in the database.
     * Delegates to {@code EntityManager.merge()}, which handles both insert and
     * update logic.
     * Ensures transactional context is present and logs key steps and exceptions.
     *
     * @param entityManager the EntityManager used to perform the operation
     * @param entity        the entity to be saved or updated
     * @return an Optional containing the managed instance of the entity
     * @throws DaoException if the entity is invalid or if no active transaction is
     *                      present
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
            throw new DaoException(errMessage, exception);

        } catch (TransactionRequiredException exception) {
            String errMessage = String.format("Failed to save or update entity of type '%s'. A transaction is required",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Deletes the given entity from the database.
     * Verifies that the entity is managed by the {@code EntityManager} before
     * attempting removal.
     * If the entity is detached, a {@code DaoException} is thrown to prevent silent
     * failure.
     *
     * @param entityManager the EntityManager used to perform the deletion
     * @param entity        the entity to delete
     * @throws DaoException if the entity is not managed, if arguments are invalid,
     *                      or if no active transaction is present
     */
    @Override
    public void delete(EntityManager entityManager, T entity) throws DaoException {
        try {
            logger.info("Attempting to delete entity of type '{}'", entityClass.getSimpleName());

            if (!entityManager.contains(entity)) {
                String errMessage = String.format("Entity of type '%s' is detached and cannot be deleted directly." +
                        "Please persist it first if needed.", entityClass.getSimpleName());
                logger.error(errMessage);
                throw new DaoException(errMessage);

            }

            entityManager.remove(entity);
        } catch (IllegalArgumentException exception) {
            String errMessage = String.format("Failed to delete entity of type '%s'." +
                    "Invalid argument provided.",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(errMessage, exception);

        } catch (TransactionRequiredException exception) {
            String errMessage = String.format("Failed to delete entity of type '%s'. A transaction is required",
                    entityClass.getSimpleName());
            logger.error(errMessage, exception);
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Retrieves a list of entities filtered by the specified field and value.
     * Validates that the field exists before executing the query.
     *
     * @param entityManager the EntityManager used to perform the query
     * @param fieldName     the name of the field to filter by
     * @param value         the value the field should match
     * @return a list of matching entities
     * @throws DaoException if the field does not exist or the query fails
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
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Builds a JPQL SELECT query to retrieve entities filtered by a single field
     * and its value.
     * The field is dynamically injected into the query string.
     *
     * @param entityManager the EntityManager used to create the query
     * @param fieldName     the name of the field to filter by
     * @param value         the value to match against the specified field
     * @return a TypedQuery ready to be executed
     * @throws DaoException if the query construction fails
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
            throw new DaoException(errMessage, exception);
        }
    }

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
     * Fetches a single entity by a given field and value.
     * If multiple results are found, only the first one is returned (without
     * enforcing uniqueness).
     *
     * @param entityManager the EntityManager used to perform the query
     * @param fieldName     the name of the field to filter by
     * @param value         the value that the field must match
     * @return an Optional containing the first matching entity, or empty if none
     *         found
     * @throws DaoException if any error occurs during the query execution
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
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Attempts to retrieve a single, unique entity of the given type based on a
     * specified field and value.
     * <p>
     * This method enforces uniqueness: if more than one matching entity is found,
     * a {@link NonUniqueFieldException} is thrown internally and wrapped in a
     * {@link DaoException}.
     * <p>
     * The field name is validated beforehand to prevent runtime query errors.
     *
     * @param entityManager the EntityManager to use for the query
     * @param fieldName     the name of the field to filter by
     * @param value         the value to match against the field
     * @return an {@link Optional} containing the matching entity if found, or empty
     *         if no match exists
     * @throws DaoException if the query fails or if the result is not unique
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
            throw new DaoException(errMessage, exception);
        }
    }

    /**
     * Validates that the provided list contains at most one entity, ensuring
     * uniqueness.
     * <p>
     * This method is typically used after executing a query that is expected to
     * return a unique result.
     * If more than one entity is found, a {@link NonUniqueFieldException} is
     * thrown.
     * Detailed logs are written at both error and debug levels to assist in
     * diagnostics.
     *
     * @param list the list of entities to validate
     * @throws NonUniqueFieldException if the list contains more than one entity
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

            throw new NonUniqueFieldException(errMessage);
        }
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

}