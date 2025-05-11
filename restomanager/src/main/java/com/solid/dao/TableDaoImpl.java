package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.ITableDao;
import com.solid.entities.TableEntity;

/**
 * Implementation of the {@link ITableDao} interface for managing
 * {@link TableEntity} entities.
 * 
 * This class extends {@link AbstractRestaurantRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link TableEntity}, which represent tables in a
 * restaurant.
 * 
 * It allows interaction with the database to perform CRUD operations and
 * queries related to
 * the tables available within a restaurant.
 */
public class TableDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<TableEntity, Integer>
        implements ITableDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public TableDaoImpl() {
        super(TableEntity.class);
    }

}
