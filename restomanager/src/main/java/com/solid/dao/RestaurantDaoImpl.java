package com.solid.dao;

import java.util.Optional;

import com.solid.dao.abstracts.AbstractDaoImpl;
import com.solid.dao.interfaces.IRestaurantDao;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;

/**
 * Implementation of the {@link IRestaurantDao} interface for managing
 * {@link RestaurantEntity} entities.
 * 
 * This class extends {@link AbstractDaoImpl} and provides the concrete
 * implementation
 * of DAO methods specific to {@link RestaurantEntity}. It allows interaction
 * with the database
 * to perform CRUD operations and queries related to restaurants.
 * 
 * It includes a custom implementation for finding a restaurant entity by its
 * unique identifier.
 */
public class RestaurantDaoImpl
        extends AbstractDaoImpl<RestaurantEntity, Integer>
        implements IRestaurantDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public RestaurantDaoImpl() {
        super(RestaurantEntity.class);
    }

    /**
     * Finds a unique {@link RestaurantEntity} by its identifier.
     * 
     * This method uses the identifier field to locate a unique restaurant entity
     * within the database.
     *
     * @param entityManager the {@link EntityManager} to use for the query
     * @param identifier    the unique identifier of the restaurant
     * @return an {@link Optional} containing the restaurant if found, or empty if
     *         not found
     * @throws DaoException if the operation fails
     */
    @Override
    public Optional<RestaurantEntity> findUniqueByIdentifier(
            EntityManager entityManager,
            String identifier)
            throws DaoException {
        return super.findUniqueBy(entityManager, "identifier", identifier);
    }

}
