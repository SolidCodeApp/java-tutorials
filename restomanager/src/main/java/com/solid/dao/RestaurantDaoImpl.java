package com.solid.dao;

import java.util.Optional;
import com.solid.dao.abstracts.AbstractDaoImpl;
import com.solid.dao.interfaces.IRestaurantDao;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * DAO implementation for Restaurant entities.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class RestaurantDaoImpl
        extends AbstractDaoImpl<RestaurantEntity, Integer>
        implements IRestaurantDao {

    public RestaurantDaoImpl() {
        super(RestaurantEntity.class);
    }

    /**
     * Finds a unique RestaurantEntity by its identifier.
     *
     * @param entityManager The EntityManager to use.
     * @param identifier    The identifier to search for.
     * @return An Optional containing the found RestaurantEntity, or empty if none
     *         found.
     * @throws DaoException If any data access errors occur.
     */
    @Override
    public Optional<RestaurantEntity> findUniqueByIdentifier(
            EntityManager entityManager,
            String identifier)
            throws DaoException {
        return super.findUniqueBy(entityManager, "identifier", identifier);
    }
}
