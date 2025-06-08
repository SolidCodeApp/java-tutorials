package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderDao;
import com.solid.entities.OrderEntity;

/**
 * DAO implementation for Order entities related to a Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class OrderDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<OrderEntity, Integer>
        implements IOrderDao {

    public OrderDaoImpl() {
        super(OrderEntity.class);
    }

}
