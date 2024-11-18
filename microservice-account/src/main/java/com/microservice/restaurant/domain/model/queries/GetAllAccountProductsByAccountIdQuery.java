package com.microservice.restaurant.domain.model.queries;

public record GetAllAccountProductsByAccountIdQuery(Long accountId){
    public GetAllAccountProductsByAccountIdQuery {
        if (accountId == null) throw new IllegalArgumentException("accountId cannot be null");
    }
}
