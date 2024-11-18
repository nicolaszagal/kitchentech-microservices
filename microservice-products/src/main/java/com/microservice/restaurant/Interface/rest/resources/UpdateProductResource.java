package com.microservice.restaurant.Interface.rest.resources;

public record UpdateProductResource (
        Long id,
        String productName,
        Double productPrice,
        String productImageUrl,
        String category,
        Long restaurantId
){
    public UpdateProductResource {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (productName == null) {
            throw new IllegalArgumentException("productName cannot be null");
        }
        if (productPrice == null) {
            throw new IllegalArgumentException("productPrice cannot be null");
        }
        if (category == null) {
            throw new IllegalArgumentException("category cannot be null");
        }
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId cannot be null");
        }
    }
}
