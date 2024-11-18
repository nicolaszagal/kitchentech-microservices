package com.microservice.restaurant.Interface.rest.resources;

public record ProductResource(
        String productName,
        Double productPrice,
        String productImageUrl,
        String category,
        Long restaurantId
) {
}
