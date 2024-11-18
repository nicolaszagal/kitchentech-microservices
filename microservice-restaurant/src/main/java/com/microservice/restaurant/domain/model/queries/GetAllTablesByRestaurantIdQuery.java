package com.microservice.restaurant.domain.model.queries;

public record GetAllTablesByRestaurantIdQuery(Long restaurantId) {
    public GetAllTablesByRestaurantIdQuery {
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId cannot be null");
        }
    }
}
