package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IPlateDao;
import com.solid.entities.PlateEntity;

/**
 * DAO implementation for Plate entities related to a Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class PlateDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<PlateEntity, Integer>
        implements IPlateDao {

    public PlateDaoImpl() {
        super(PlateEntity.class);
    }
}