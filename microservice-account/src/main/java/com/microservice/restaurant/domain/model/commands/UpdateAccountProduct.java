package com.microservice.restaurant.domain.model.commands;

public record UpdateAccountProduct(
        Long id,
        Long productId,
        Long accountId,
        Double price,
        Integer quantity
) {
    public UpdateAccountProduct {
        if (id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        if (productId == null){
            throw new IllegalArgumentException("productId cannot be null");
        }
        if (accountId == null){
            throw new IllegalArgumentException("accountId cannot be null");
        }
        if (price == null){
            throw new IllegalArgumentException("price cannot be null");
        }
        if (quantity == null){
            throw new IllegalArgumentException("quantity cannot be null");
        }
    }
}
