package com.solid.dao.interfaces;

import com.solid.entities.MenuItemEntity;

/**
 * Data Access Object (DAO) interface for {@link MenuItemEntity}.
 *
 * Extends the generic {@link IDao} interface to provide CRUD operations
 * and common query methods for menu item entities.
 *
 * This interface defines the DAO contract for managing MenuItem records,
 * typically identified by an integer ID.
 */
public interface IMenuItemDao extends IDao<MenuItemEntity, Integer> {

}
