package com.solid.dao;

import com.solid.dao.abstracts.AbstractDaoImpl;
import com.solid.dao.interfaces.IMenuItemDao;
import com.solid.entities.MenuItemEntity;

/**
 * Implementation of the {@link IMenuItemDao} interface for managing
 * {@link MenuItemEntity} entities.
 * 
 * This class extends {@link AbstractDaoImpl} and provides the concrete
 * implementation
 * of DAO methods specific to {@link MenuItemEntity}.
 * It handles database operations related to menu items, typically including
 * CRUD operations
 * and other queries for managing items within a restaurant's menu.
 */
public class MenuItemDaoImpl
        extends AbstractDaoImpl<MenuItemEntity, Integer>
        implements IMenuItemDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public MenuItemDaoImpl() {
        super(MenuItemEntity.class);
    }

}
