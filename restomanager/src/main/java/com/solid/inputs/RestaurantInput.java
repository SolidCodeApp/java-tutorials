package com.solid.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class representing input data required to create or update a restaurant.
 * This is typically used in service or controller layers.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInput {
    private String name;
    private String address;
    private String description;
    private String owner;
}
