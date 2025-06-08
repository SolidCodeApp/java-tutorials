package com.solid.dao.interfaces;

import com.solid.entities.OrderTableEntity;

/**
 * DAO interface for OrderTable entities.
 * Extends order-related DAO to provide access specific to OrderTable.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IOrderTableDao extends IOrderRelatedDao<OrderTableEntity, Integer> {

}
