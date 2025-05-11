package com.solid.dao.abstracts;

import java.util.List;

import com.solid.dao.interfaces.IOrderRelatedDao;
import com.solid.entities.OrderEntity;
import com.solid.exceptions.DaoException;

import jakarta.persistence.EntityManager;

public class AbstractOrderRelatedDaoImpl<T, ID>
        extends AbstractDaoImpl<T, ID>
        implements IOrderRelatedDao<T, ID> {

    public AbstractOrderRelatedDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<T> findByOrder(EntityManager entityManager, OrderEntity order) throws DaoException {
        return super.findBy(entityManager, "order", order);
    }
}
