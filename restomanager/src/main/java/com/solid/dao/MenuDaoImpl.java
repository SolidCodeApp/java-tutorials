package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.IMenuDao;
import com.solid.entities.MenuEntity;

/**
 * DAO implementation for Menu entities related to a Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class MenuDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<MenuEntity, Integer>
        implements IMenuDao {

    public MenuDaoImpl() {
        super(MenuEntity.class);
    }

}
