package com.microservice.restaurant.domain.model.queries;

public record GetRestaurantByIdQuery(Long id) {
    public GetRestaurantByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
