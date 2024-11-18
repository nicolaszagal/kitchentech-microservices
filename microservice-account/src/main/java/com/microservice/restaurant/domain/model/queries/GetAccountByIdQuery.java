package com.microservice.restaurant.domain.model.queries;

public record GetAccountByIdQuery (Long id){
    public GetAccountByIdQuery {
        if (id == null){
            throw new NullPointerException("id is null");
        }
    }
}
