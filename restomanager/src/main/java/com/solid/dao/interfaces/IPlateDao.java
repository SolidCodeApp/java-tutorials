package com.solid.dao.interfaces;

import com.solid.entities.PlateEntity;

/**
 * Data Access Object (DAO) interface for {@link PlateEntity}.
 * 
 * Extends the {@link IRestaurantRelatedDao} interface to manage entities that
 * represent
 * plates or dishes in a restaurant's menu.
 * 
 * This interface defines the DAO contract for handling {@link PlateEntity}
 * records,
 * which are typically identified by an integer ID and are associated with a
 * specific restaurant.
 */
public interface IPlateDao extends IRestaurantRelatedDao<PlateEntity, Integer> {

}
