package com.microservice.restaurant.domain.model.commands;

public record CreateTableCommand (
        Long tableNumber,
        Long tableCapacity,
        Long restaurantId
){
    public CreateTableCommand {
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
