package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IMenuDao;
import com.solid.entities.MenuEntity;

/**
 * Implementation of the {@link IMenuDao} interface for managing
 * {@link MenuEntity} entities.
 * 
 * This class extends {@link AbstractRestaurantRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link MenuEntity} for a restaurant context.
 * It allows interaction with the database to perform CRUD operations and
 * queries
 * related to restaurant menus.
 */
public class MenuDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<MenuEntity, Integer>
        implements IMenuDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public MenuDaoImpl() {
        super(MenuEntity.class);
    }

}
