package com.solid.dao.interfaces;

import com.solid.entities.OrderDishEntity;

/**
 * Data Access Object (DAO) interface for {@link OrderDishEntity}.
 * 
 * Extends the {@link IOrderRelatedDao} interface to manage entities that are
 * specifically related to an order and represent individual dishes in that
 * order.
 * 
 * This interface defines the DAO contract for handling {@link OrderDishEntity}
 * records,
 * which are typically identified by an integer ID and linked to a particular
 * order.
 */
public interface IOrderDishDao extends IOrderRelatedDao<OrderDishEntity, Integer> {

}
