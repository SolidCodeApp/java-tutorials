package com.solid.services.interfaces;

import java.util.List;
import java.util.Optional;
import com.solid.exceptions.services.RestaurantServiceException;
import com.solid.inputs.RestaurantInput;
import com.solid.models.RestaurantModel;

/**
 * Interface defining business operations related to Restaurant entities.
 * Provides methods for creating, updating, finding, and deleting restaurants.
 * 
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface IRestaurantService {

        /**
         * Creates a new restaurant based on the provided input.
         */
        Optional<RestaurantModel> createRestaurant(RestaurantInput input)
                        throws RestaurantServiceException;

        /**
         * Updates an existing restaurant identified by the given ID.
         */
        Optional<RestaurantModel> updateRestaurant(RestaurantInput input, Integer id)
                        throws RestaurantServiceException;

        /**
         * Finds a restaurant by its database ID.
         */
        Optional<RestaurantModel> findRestaurantById(Integer id)
                        throws RestaurantServiceException;

        /**
         * Finds a restaurant by its business identifier.
         */
        Optional<RestaurantModel> findRestaurantByIdentifier(String identifier)
                        throws RestaurantServiceException;

        /**
         * Deletes a restaurant by its ID.
         */
        void deleteRestaurant(Integer id)
                        throws RestaurantServiceException;

        /**
         * Retrieves all restaurants owned by the specified owner.
         */
        List<RestaurantModel> findAllRestaurantsByOwner(String owner)
                        throws RestaurantServiceException;
}