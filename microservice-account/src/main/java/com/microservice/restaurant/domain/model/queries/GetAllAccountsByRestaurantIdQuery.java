package com.microservice.restaurant.domain.model.queries;

public record GetAllAccountsByRestaurantIdQuery(Long restaurantId){
    public GetAllAccountsByRestaurantIdQuery {
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId cannot be null");
        }
    }
}
