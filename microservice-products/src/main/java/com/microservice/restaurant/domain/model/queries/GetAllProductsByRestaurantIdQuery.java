package com.microservice.restaurant.domain.model.queries;

public record GetAllProductsByRestaurantIdQuery(Long restaurantId) {
    public GetAllProductsByRestaurantIdQuery {
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId cannot be null");
        }
    }
}
