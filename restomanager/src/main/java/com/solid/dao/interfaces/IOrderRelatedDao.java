package com.solid.dao.interfaces;

import java.util.List;
import com.solid.entities.OrderEntity;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * DAO interface for entities related to an Order.
 * Provides methods to access entities filtered by Order.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IOrderRelatedDao<T, ID> extends IDao<T, ID> {

    /**
     * Finds a list of entities related to a specific Order.
     *
     * @param entityManager the JPA EntityManager
     * @param order         the OrderEntity to filter by
     * @return a list of entities related to the given Order
     * @throws DaoException if data access fails
     */
    public List<T> findByOrder(EntityManager entityManager, OrderEntity order) throws DaoException;

}
