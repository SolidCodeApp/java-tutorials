package com.solid.dao.interfaces;

import com.solid.entities.TableEntity;

/**
 * Data Access Object (DAO) interface for {@link TableEntity}.
 * 
 * Extends the {@link IRestaurantRelatedDao} interface to manage entities that
 * represent
 * tables within a restaurant.
 * 
 * This interface defines the DAO contract for handling {@link TableEntity}
 * records,
 * which are typically identified by an integer ID and associated with a
 * specific restaurant.
 */
public interface ITableDao extends IRestaurantRelatedDao<TableEntity, Integer> {

}
