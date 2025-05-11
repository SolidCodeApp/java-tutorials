package com.solid.dao.interfaces;

import com.solid.entities.TicketEntity;

/**
 * Data Access Object (DAO) interface for {@link TicketEntity}.
 * 
 * Extends the {@link IRestaurantRelatedDao} interface to manage entities that
 * represent
 * tickets in a restaurant setting, typically associated with customer orders or
 * transactions.
 * 
 * This interface defines the DAO contract for handling {@link TicketEntity}
 * records,
 * which are typically identified by an integer ID and are associated with a
 * specific restaurant.
 */
public interface ITicketDao extends IRestaurantRelatedDao<TicketEntity, Integer> {

}
