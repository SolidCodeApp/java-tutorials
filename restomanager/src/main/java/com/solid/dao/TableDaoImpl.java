package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.ITableDao;
import com.solid.entities.TableEntity;

/**
 * DAO implementation for Table entities related to a Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class TableDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<TableEntity, Integer>
        implements ITableDao {

    /**
     * Default constructor initializing with TableEntity class.
     */
    public TableDaoImpl() {
        super(TableEntity.class);
    }

}
