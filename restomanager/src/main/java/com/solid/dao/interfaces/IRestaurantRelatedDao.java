package com.solid.dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * DAO interface for entities related to a Restaurant.
 * Provides methods to find entities by restaurant and date range.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IRestaurantRelatedDao<T, ID> extends IDao<T, ID> {

        /**
         * Finds a unique entity related to a specific restaurant by identifier.
         *
         * @param entityManager    the EntityManager for the query
         * @param targetIdentifier the identifier of the target entity
         * @param restaurant       the related Restaurant entity
         * @return an Optional containing the entity if found, empty otherwise
         * @throws DaoException if a data access error occurs
         */
        public Optional<T> findUniqueByRestaurant(
                        EntityManager entityManager,
                        String targetIdentifier,
                        RestaurantEntity restaurant) throws DaoException;

        /**
         * Finds a list of entities related to a specific restaurant.
         *
         * @param entityManager the EntityManager for the query
         * @param restaurant    the related Restaurant entity
         * @return list of entities related to the restaurant
         * @throws DaoException if a data access error occurs
         */
        public List<T> findByRestaurant(
                        EntityManager entityManager,
                        RestaurantEntity restaurant) throws DaoException;

        /**
         * Finds a list of entities related to a restaurant within a creation date
         * range.
         *
         * @param entityManager the EntityManager for the query
         * @param restaurant    the related Restaurant entity
         * @param startDate     the start of the date range (inclusive)
         * @param endDate       the end of the date range (inclusive)
         * @return list of entities matching the criteria
         * @throws DaoException if a data access error occurs
         */
        public List<T> findByRestaurantWithCreationDateRange(
                        EntityManager entityManager,
                        RestaurantEntity restaurant,
                        LocalDateTime startDate,
                        LocalDateTime endDate) throws DaoException;
}
