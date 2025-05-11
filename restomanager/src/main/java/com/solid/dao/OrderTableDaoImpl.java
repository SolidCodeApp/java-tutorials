package com.solid.dao;

import com.solid.dao.abstracts.AbstractOrderRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderTableDao;
import com.solid.entities.OrderTableEntity;

/**
 * Implementation of the {@link IOrderTableDao} interface for managing
 * {@link OrderTableEntity} entities.
 * 
 * This class extends {@link AbstractOrderRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link OrderTableEntity}, which represent the
 * relationship between
 * a specific order and the table assigned to it in a restaurant setting.
 * 
 * It allows interaction with the database to perform CRUD operations and
 * queries related to
 * the table information associated with an order in a restaurant.
 */
public class OrderTableDaoImpl
        extends AbstractOrderRelatedDaoImpl<OrderTableEntity, Integer>
        implements IOrderTableDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public OrderTableDaoImpl() {
        super(OrderTableEntity.class);
    }
}
