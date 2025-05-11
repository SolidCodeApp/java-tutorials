package com.solid.dao.interfaces;

import com.solid.entities.OrderEntity;

/**
 * Data Access Object (DAO) interface for {@link OrderEntity}.
 * 
 * Extends the {@link IRestaurantRelatedDao} interface to include additional
 * behavior specific to managing {@link OrderEntity} entities in the context
 * of a restaurant.
 *
 * This interface defines the DAO contract for handling order records, which
 * are typically associated with a particular restaurant and identified by
 * an integer ID.
 */
public interface IOrderDao extends IRestaurantRelatedDao<OrderEntity, Integer> {

}
