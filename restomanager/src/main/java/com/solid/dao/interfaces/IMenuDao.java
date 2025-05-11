package com.solid.dao.interfaces;

import com.solid.entities.MenuEntity;

/**
 * Data Access Object (DAO) interface for {@link MenuEntity}.
 * 
 * Extends {@link IRestaurantRelatedDao} to include additional
 * behavior specific to entities related to a restaurant.
 *
 * This interface defines the DAO contract for Menu entities,
 * which are typically linked to a restaurant and managed via
 * their unique integer identifiers.
 */
public interface IMenuDao extends IRestaurantRelatedDao<MenuEntity, Integer> {

}
