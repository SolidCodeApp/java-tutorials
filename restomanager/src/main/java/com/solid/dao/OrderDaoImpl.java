package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderDao;
import com.solid.entities.OrderEntity;

/**
 * Implementation of the {@link IOrderDao} interface for managing
 * {@link OrderEntity} entities.
 * 
 * This class extends {@link AbstractRestaurantRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link OrderEntity}. It allows interaction with
 * the database to perform
 * CRUD operations and queries related to customer orders in a restaurant
 * context.
 */
public class OrderDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<OrderEntity, Integer>
        implements IOrderDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public OrderDaoImpl() {
        super(OrderEntity.class);
    }

}
