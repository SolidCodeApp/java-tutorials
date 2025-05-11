package com.solid.dao;

import com.solid.dao.abstracts.AbstractOrderRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderDishDao;
import com.solid.entities.OrderDishEntity;

/**
 * Implementation of the {@link IOrderDishDao} interface for managing
 * {@link OrderDishEntity} entities.
 * 
 * This class extends {@link AbstractOrderRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link OrderDishEntity}, which represent dishes in
 * a specific order.
 * 
 * It allows interaction with the database to perform CRUD operations and
 * queries related to
 * the dishes within a customer order in a restaurant context.
 */
public class OrderDishDaoImpl
        extends AbstractOrderRelatedDaoImpl<OrderDishEntity, Integer>
        implements IOrderDishDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public OrderDishDaoImpl() {
        super(OrderDishEntity.class);
    }

}
