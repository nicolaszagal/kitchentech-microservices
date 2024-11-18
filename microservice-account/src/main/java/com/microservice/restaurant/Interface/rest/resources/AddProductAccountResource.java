package com.microservice.restaurant.Interface.rest.resources;

public record AddProductAccountResource(
        Long productId,
        Long accountId
) {
    public AddProductAccountResource {
        if (productId == null){
            throw new IllegalArgumentException("productId cannot be null");
        }
        if (accountId == null){
            throw new IllegalArgumentException("accountId cannot be null");
        }
    }
}
