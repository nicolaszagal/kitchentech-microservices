package com.microservice.restaurant.domain.model.commands;

public record AddAccountProduct(
        Long productId,
        Long accountId
) {
    public AddAccountProduct {
        if (productId == null){
            throw new IllegalArgumentException("productId cannot be null");
        }
        if (accountId == null){
            throw new IllegalArgumentException("accountId cannot be null");
        }
    }
}
