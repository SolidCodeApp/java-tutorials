package com.solid.dao.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solid.dao.interfaces.IRestaurantRelatedDao;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public abstract class AbstractRestaurantRelatedDaoImpl<T, ID>
        extends AbstractDaoImpl<T, ID>
        implements IRestaurantRelatedDao<T, ID> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestaurantRelatedDaoImpl.class);

    public AbstractRestaurantRelatedDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

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
            throw new DaoException(errMessage, exception);
        }
    }

    @Override
    public List<T> findByRestaurant(EntityManager entityManager, RestaurantEntity restaurant) throws DaoException {
        return super.findBy(entityManager, "restaurant", restaurant);
    }

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
            throw new DaoException(errMessage, exception);
        }
    }

}
