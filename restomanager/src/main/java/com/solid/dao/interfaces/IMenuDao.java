package com.solid.dao.interfaces;

import com.solid.entities.MenuEntity;

/**
 * DAO interface for Menu entities.
 * Extends the restaurant-related DAO to provide access methods scoped to a
 * restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IMenuDao extends IRestaurantRelatedDao<MenuEntity, Integer> {

}
