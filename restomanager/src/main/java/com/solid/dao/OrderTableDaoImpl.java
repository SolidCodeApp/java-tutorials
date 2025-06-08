package com.solid.dao;

import com.solid.dao.abstracts.AbstractOrderRelatedDaoImpl;
import com.solid.dao.interfaces.IOrderTableDao;
import com.solid.entities.OrderTableEntity;

/**
 * DAO implementation for OrderTable entities related to an Order.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class OrderTableDaoImpl
        extends AbstractOrderRelatedDaoImpl<OrderTableEntity, Integer>
        implements IOrderTableDao {

    public OrderTableDaoImpl() {
        super(OrderTableEntity.class);
    }
}