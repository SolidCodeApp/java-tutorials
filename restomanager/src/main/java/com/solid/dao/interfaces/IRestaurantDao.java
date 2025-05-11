package com.solid.dao.interfaces;

import java.util.Optional;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;

/**
 * Data Access Object (DAO) interface for {@link RestaurantEntity}.
 * 
 * Extends the generic {@link IDao} interface to provide CRUD operations
 * and common query methods specifically for {@link RestaurantEntity}.
 * 
 * This interface defines the DAO contract for handling restaurant entities,
 * typically identified by an integer ID.
 */
public interface IRestaurantDao extends IDao<RestaurantEntity, Integer> {

    /**
     * Finds a unique {@link RestaurantEntity} by its identifier.
     * 
     * This method allows querying a restaurant by a unique identifier, such as
     * a restaurant code or slug, ensuring that the entity is uniquely associated
     * with that identifier.
     *
     * @param entityManager the {@link EntityManager} to use for the query
     * @param identifier    the unique identifier of the restaurant
     * @return an {@link Optional} containing the restaurant if found, or empty if
     *         not found
     * @throws DaoException if the operation fails
     */
    public Optional<RestaurantEntity> findUniqueByIdentifier(
            EntityManager entityManager,
            String identifier)
            throws DaoException;
}
