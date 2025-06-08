package com.solid.dao;

import com.solid.dao.abstracts.AbstractRestaurantRelatedDaoImpl;
import com.solid.dao.interfaces.ITicketDao;
import com.solid.entities.TicketEntity;

/**
 * DAO implementation for Ticket entities related to a Restaurant.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class TicketDaoImpl
        extends AbstractRestaurantRelatedDaoImpl<TicketEntity, Integer>
        implements ITicketDao {

    /**
     * Default constructor initializing with TicketEntity class.
     */
    public TicketDaoImpl() {
        super(TicketEntity.class);
    }
}