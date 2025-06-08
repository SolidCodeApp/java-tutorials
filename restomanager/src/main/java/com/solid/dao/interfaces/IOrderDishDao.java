package com.solid.dao.interfaces;

import com.solid.entities.OrderDishEntity;

/**
 * DAO interface for OrderDish entities related to an Order.
 * Provides data access operations scoped by Order.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IOrderDishDao extends IOrderRelatedDao<OrderDishEntity, Integer> {

}
