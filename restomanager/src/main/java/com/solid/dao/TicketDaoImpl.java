package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.ITicketDao;
import com.solid.entities.TicketEntity;

/**
 * Implementation of the {@link ITicketDao} interface for managing
 * {@link TicketEntity} entities.
 * 
 * This class extends {@link AbstractRestaurantRelatedDaoImpl} and provides the
 * concrete implementation
 * of DAO methods specific to {@link TicketEntity}, which represent tickets in a
 * restaurant context,
 * typically associated with customer orders or transactions.
 * 
 * It allows interaction with the database to perform CRUD operations and
 * queries related to
 * the tickets issued in a restaurant.
 */
public class TicketDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<TicketEntity, Integer>
        implements ITicketDao {

    /**
     * Default constructor that passes the entity class type to the superclass
     * constructor.
     */
    public TicketDaoImpl() {
        super(TicketEntity.class);
    }

}
