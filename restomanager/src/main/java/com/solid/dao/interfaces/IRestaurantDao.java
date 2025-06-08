package com.solid.dao.interfaces;

import java.util.Optional;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * DAO interface for Restaurant entities.
 * Provides data access methods specific to Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IRestaurantDao extends IDao<RestaurantEntity, Integer> {

    /**
     * Finds a unique Restaurant entity by its identifier.
     *
     * @param entityManager the EntityManager to use for the query
     * @param identifier    the unique identifier of the restaurant
     * @return an Optional containing the Restaurant entity if found, empty
     *         otherwise
     * @throws DaoException if a data access error occurs
     */
    public Optional<RestaurantEntity> findUniqueByIdentifier(
            EntityManager entityManager,
            String identifier)
            throws DaoException;
}
