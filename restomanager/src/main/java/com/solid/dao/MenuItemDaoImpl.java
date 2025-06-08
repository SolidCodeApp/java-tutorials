package com.solid.dao;

import com.solid.dao.abstracts.AbstractDaoImpl;
import com.solid.dao.interfaces.IMenuItemDao;
import com.solid.entities.MenuItemEntity;

/**
 * DAO implementation for MenuItem entities.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class MenuItemDaoImpl
        extends AbstractDaoImpl<MenuItemEntity, Integer>
        implements IMenuItemDao {

    public MenuItemDaoImpl() {
        super(MenuItemEntity.class);
    }

}
