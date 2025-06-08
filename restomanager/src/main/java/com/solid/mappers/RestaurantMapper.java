package com.solid.mappers;

import com.solid.entities.RestaurantEntity;
import com.solid.inputs.RestaurantInput;
import com.solid.models.RestaurantModel;

/**
 * Utility class responsible for mapping between
 * RestaurantInput/RestaurantEntity and RestaurantModel.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public final class RestaurantMapper {

    /**
     * Converts a RestaurantInput object to a RestaurantEntity.
     *
     * @param input the input object containing restaurant data
     * @return a RestaurantEntity based on the provided input
     */
    public static RestaurantEntity toEntity(RestaurantInput input) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setName(input.getName());
        entity.setAddress(input.getAddress());
        entity.setOwner(input.getOwner());
        entity.setDescription(input.getDescription());
        return entity;
    }

    /**
     * Converts a RestaurantEntity object to a RestaurantModel.
     *
     * @param entity the entity representing a restaurant in persistence
     * @return a RestaurantModel based on the provided entity
     */
    public static RestaurantModel toModel(RestaurantEntity entity) {
        RestaurantModel model = new RestaurantModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setAddress(entity.getAddress());
        model.setOwner(entity.getOwner());
        model.setDescription(entity.getDescription());
        model.setIdentifier(entity.getIdentifier());
        return model;
    }
}
