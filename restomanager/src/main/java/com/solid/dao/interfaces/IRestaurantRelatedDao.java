package com.solid.dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;

/**
 * Specialized DAO interface for entities associated with a
 * {@link RestaurantEntity}.
 * 
 * Extends the generic {@link IDao} interface and adds methods tailored for
 * retrieving or filtering entities in the context of a specific restaurant.
 *
 * @param <T>  the type of entity managed by this DAO
 * @param <ID> the type of the primary key of the entity
 */
public interface IRestaurantRelatedDao<T, ID> extends IDao<T, ID> {

        /**
         * Finds a unique entity of type {@code T} by a given identifier field and its
         * associated restaurant.
         * 
         * This method ensures that the entity is uniquely related to the provided
         * restaurant context,
         * and can be used for business identifiers like slugs or codes that are unique
         * within a restaurant.
         *
         * @param entityManager    the {@link EntityManager} used to perform the query
         * @param targetIdentifier the name of the identifying field (e.g., code, slug)
         * @param restaurant       the {@link RestaurantEntity} in which context the
         *                         search is performed
         * @return an {@link Optional} containing the entity if found, or empty if not
         *         found
         * @throws DaoException if the query fails or multiple results are found
         */
        public Optional<T> findUniqueByRestaurant(
                        EntityManager entityManager,
                        String targetIdentifier,
                        RestaurantEntity restaurant) throws DaoException;

        /**
         * Retrieves all entities of type {@code T} associated with the specified
         * restaurant.
         *
         * @param entityManager the {@link EntityManager} to use for querying
         * @param restaurant    the {@link RestaurantEntity} context
         * @return a list of entities belonging to the given restaurant
         * @throws DaoException if the query execution fails
         */
        public List<T> findByRestaurant(
                        EntityManager entityManager,
                        RestaurantEntity restaurant) throws DaoException;

        /**
         * Retrieves all entities of type {@code T} associated with a specific
         * restaurant
         * and created within a given date-time range.
         *
         * @param entityManager the {@link EntityManager} to use for querying
         * @param restaurant    the {@link RestaurantEntity} to filter by
         * @param startDate     the start of the creation date range (inclusive)
         * @param endDate       the end of the creation date range (inclusive)
         * @return a list of entities matching the restaurant and date range criteria
         * @throws DaoException if the query fails
         */
        public List<T> findByRestaurantWithCreationDateRange(
                        EntityManager entityManager,
                        RestaurantEntity restaurant,
                        LocalDateTime startDate,
                        LocalDateTime endDate) throws DaoException;
}
