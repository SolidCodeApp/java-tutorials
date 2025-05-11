package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IPlateDao;
import com.solid.entities.PlateEntity;

/**
 * Implementation of the {@link IPlateDao} interface for managing
 * {@link PlateEntity} entities.
 * 
 * This class extends {@link AbstractRestaurantRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link PlateEntity}, which represent plates or
 * dishes within a restaurant's menu.
 * 
 * It allows interaction with the database to perform CRUD operations and
 * queries related to
 * the plates offered by a restaurant.
 */
public class PlateDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<PlateEntity, Integer>
        implements IPlateDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public PlateDaoImpl() {
        super(PlateEntity.class);
    }
}
