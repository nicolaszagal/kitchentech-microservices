package com.microservice.restaurant.domain.model.queries;

public record GetProductByIdQuery(Long id) {
    public GetProductByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
