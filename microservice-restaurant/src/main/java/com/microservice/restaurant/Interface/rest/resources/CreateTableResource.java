package com.microservice.restaurant.Interface.rest.resources;

public record CreateTableResource(
        Long tableNumber,
        Long tableCapacity,
        Long restaurantId
) {
    public CreateTableResource {
        if (tableNumber == null) {
            throw new IllegalArgumentException("tableNumber must not be null");
        }
        if (tableCapacity == null) {
            throw new IllegalArgumentException("tableCapacity must not be null");
        }
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId must not be null");
        }
    }
}