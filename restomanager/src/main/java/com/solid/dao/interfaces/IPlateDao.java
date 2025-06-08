package com.solid.dao.interfaces;

import com.solid.entities.PlateEntity;

/**
 * DAO interface for Plate entities.
 * Extends restaurant-related DAO to provide access specific to Plate.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IPlateDao extends IRestaurantRelatedDao<PlateEntity, Integer> {

}
