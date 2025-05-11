package com.solid.dao.interfaces;

import com.solid.entities.OrderTableEntity;

/**
 * Data Access Object (DAO) interface for {@link OrderTableEntity}.
 * 
 * Extends the {@link IOrderRelatedDao} interface to manage entities that
 * represent
 * the relationship between an order and a table in a restaurant.
 * 
 * This interface defines the DAO contract for handling {@link OrderTableEntity}
 * records,
 * which are typically identified by an integer ID and associated with a
 * specific order and table.
 */
public interface IOrderTableDao extends IOrderRelatedDao<OrderTableEntity, Integer> {

}
