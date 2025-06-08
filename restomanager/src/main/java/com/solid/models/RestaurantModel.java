package com.solid.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) representing a restaurant in the service layer.
 * Encapsulates the necessary fields for business operations and external
 * exposure.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantModel {

    private Integer id;
    private String name;
    private String address;
    private String description;
    private String owner;
    private String identifier;
    private LocalDateTime creationDate;
}