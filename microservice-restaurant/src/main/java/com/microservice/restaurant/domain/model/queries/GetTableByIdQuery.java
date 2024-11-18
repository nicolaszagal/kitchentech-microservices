package com.microservice.restaurant.domain.model.queries;

public record GetTableByIdQuery(Long id) {
    public GetTableByIdQuery {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
    }
}
