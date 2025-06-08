package com.solid.dao;

import com.solid.dao.abstracts.AbstractOrderRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderDishDao;
import com.solid.entities.OrderDishEntity;

/**
 * DAO implementation for OrderDish entities related to an Order.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class OrderDishDaoImpl
        extends AbstractOrderRelatedDaoImpl<OrderDishEntity, Integer>
        implements IOrderDishDao {

    public OrderDishDaoImpl() {
        super(OrderDishEntity.class);
    }
}