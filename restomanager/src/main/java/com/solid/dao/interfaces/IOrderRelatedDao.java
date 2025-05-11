package com.solid.dao.interfaces;

import java.util.List;

import com.solid.entities.OrderEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;

/**
 * DAO interface for entities associated with an {@link OrderEntity}.
 * 
 * Extends the generic {@link IDao} interface to include functionality
 * specific to retrieving entities that are linked to a particular order.
 *
 * This interface is intended for use with domain entities that have a
 * relationship with orders in the system.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 */
public interface IOrderRelatedDao<T, ID> extends IDao<T, ID> {

    /**
     * Retrieves all entities of type {@code T} that are linked to the given order.
     *
     * @param entityManager the {@link EntityManager} to use for querying
     * @param order         the {@link OrderEntity} associated with the target
     *                      entities
     * @return a list of entities belonging to the specified order
     * @throws DaoException if the operation fails
     */
    public List<T> findByOrder(EntityManager entityManager, OrderEntity order) throws DaoException;

}
