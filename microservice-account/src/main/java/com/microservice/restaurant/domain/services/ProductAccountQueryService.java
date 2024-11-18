package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.queries.GetAllAccountProductsByAccountIdQuery;

import java.util.Optional;

public interface ProductAccountQueryService {

    Optional<AccountProduct> handle(GetAllAccountProductsByAccountIdQuery query);
}
