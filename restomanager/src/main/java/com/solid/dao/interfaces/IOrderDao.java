package com.solid.dao.interfaces;

import com.solid.entities.OrderEntity;

/**
 * DAO interface for Order entities related to a Restaurant.
 * Provides data access operations scoped by Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IOrderDao extends IRestaurantRelatedDao<OrderEntity, Integer> {

}
