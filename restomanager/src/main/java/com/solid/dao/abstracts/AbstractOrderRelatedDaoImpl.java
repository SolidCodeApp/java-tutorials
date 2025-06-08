package com.solid.dao.abstracts;

import java.util.List;
import com.solid.dao.interfaces.IOrderRelatedDao;
import com.solid.entities.OrderEntity;
import com.solid.exceptions.DaoException;
import jakarta.persistence.EntityManager;

/**
 * Abstract DAO implementation for entities related to OrderEntity.
 * Extends the generic AbstractDaoImpl with functionality specific to
 * order-related entities.
 * 
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class AbstractOrderRelatedDaoImpl<T, ID>
        extends AbstractDaoImpl<T, ID>
        implements IOrderRelatedDao<T, ID> {

    /**
     * Constructor initializing with the entity class type.
     *
     * @param entityClass the class of the entity this DAO manages
     */
    public AbstractOrderRelatedDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * Finds all entities linked to the specified OrderEntity.
     *
     * @param entityManager the JPA EntityManager
     * @param order         the OrderEntity instance to filter by
     * @return list of entities associated with the given order
     * @throws DaoException if the query fails
     */
    @Override
    public List<T> findByOrder(EntityManager entityManager, OrderEntity order) throws DaoException {
        return super.findBy(entityManager, "order", order);
    }
}
