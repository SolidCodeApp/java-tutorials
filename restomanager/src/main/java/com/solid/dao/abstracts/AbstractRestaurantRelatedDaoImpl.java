package com.solid.dao.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.dao.interfaces.IRestaurantRelatedDao;
import com.solid.entities.RestaurantEntity;
import com.solid.enums.EErrorCode;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Abstract DAO implementation for entities related to RestaurantEntity.
 * Extends AbstractDaoImpl with restaurant-specific query capabilities,
 * including unique fetch by restaurant and identifier, and date-range
 * filtering.
 * 
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public abstract class AbstractRestaurantRelatedDaoImpl<T, ID>
        extends AbstractDaoImpl<T, ID>
        implements IRestaurantRelatedDao<T, ID> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestaurantRelatedDaoImpl.class);

    /**
     * Constructor initializing the DAO with the entity class type.
     *
     * @param entityClass the class of the entity this DAO manages
     */
    public AbstractRestaurantRelatedDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * Finds a unique entity by its identifier and associated RestaurantEntity.
     *
     * @param entityManager    the JPA EntityManager
     * @param targetIdentifier the identifier value to filter by
     * @param restaurant       the RestaurantEntity to filter by
     * @return an Optional containing the unique entity if found
     * @throws DaoException if the query fails or multiple results are found
     */
    @Override
    public Optional<T> findUniqueByRestaurant(EntityManager entityManager, String targetIdentifier,
            RestaurantEntity restaurant) throws DaoException {

        String entityName = super.getEntityClass().getSimpleName();

        try {
            logger.info("Attempting to fetch a unique entity of type '{}'", entityName);

            String identifierFieldName = ensureFieldExists("identifier");
            String restaurantFieldName = ensureFieldExists("restaurant");

            logger.debug("Attempting to fetch a unique entity of type '{}' using key ({}, {})",
                    entityName,
                    identifierFieldName,
                    restaurantFieldName);

            String jpql = "SELECT e FROM " + entityName + " e WHERE e." +
                    identifierFieldName + " = :identifierField AND e." +
                    restaurantFieldName + " = :restaurantField" +
                    " ORDER BY e.id";

            TypedQuery<T> query = entityManager.createQuery(jpql, super.getEntityClass());

            query.setParameter("identifierField", targetIdentifier);
            query.setParameter("restaurantField", restaurant);
            query.setMaxResults(2);

            List<T> list = query.getResultList();
            super.checkUniqueness(list);
            return list.stream().findFirst();

        } catch (Exception exception) {
            String errMessage = String.format("Failed to fetch a unique entity of type '%s'," +
                    " Using fields ('identifier', 'restaurant')", entityName);

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

    /**
     * Finds all entities associated with the given RestaurantEntity.
     *
     * @param entityManager the JPA EntityManager
     * @param restaurant    the RestaurantEntity to filter by
     * @return list of entities related to the specified restaurant
     * @throws DaoException if the query fails
     */
    @Override
    public List<T> findByRestaurant(EntityManager entityManager, RestaurantEntity restaurant) throws DaoException {
        return super.findBy(entityManager, "restaurant", restaurant);
    }

    /**
     * Finds all entities related to a RestaurantEntity filtered by a creation date
     * range.
     *
     * @param entityManager the JPA EntityManager
     * @param restaurant    the RestaurantEntity to filter by
     * @param startDate     the start of the creation date range (inclusive)
     * @param endDate       the end of the creation date range (inclusive)
     * @return list of entities filtered by restaurant and creation date range
     * @throws DaoException if the query fails
     */
    @Override
    public List<T> findByRestaurantWithCreationDateRange(EntityManager entityManager,
            RestaurantEntity restaurant,
            LocalDateTime startDate,
            LocalDateTime endDate) throws DaoException {

        String entityName = super.getEntityClass().getSimpleName();

        try {
            logger.info("Attempting to fetch all entities of type {} by restaurant and by date range", entityName);

            String creationDateFieldName = super.ensureFieldExists("creationDate");
            String restaurantFieldName = super.ensureFieldExists("restaurant");

            logger.debug("Attempting to fetch all entities of type {} by restaurant" +
                    " and by date range ({}, {})", entityName, startDate, endDate);

            String jpql = "SELECT e FROM " + entityName + " WHERE e." +
                    restaurantFieldName + " = :restaurantField" +
                    " AND e.=" + creationDateFieldName + " >= :startDate" +
                    " AND e.=" + creationDateFieldName + " <= :endDate" +
                    " ORDER BY e.id";

            TypedQuery<T> query = entityManager.createQuery(jpql, super.getEntityClass());
            query.setParameter("restaurantField", restaurant);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            return query.getResultList();
        } catch (Exception exception) {
            String errMessage = String.format("Failed to fetch a list of entities of type '%s'"
                    + " by date range for a restaurant", entityName);

            logger.error(errMessage, exception);
            throw new DaoException(EErrorCode.DATA_ERROR, errMessage, exception);
        }
    }

}
