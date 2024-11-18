package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.entities.Account;
import com.microservice.restaurant.domain.model.queries.GetAccountByIdQuery;
import com.microservice.restaurant.domain.model.queries.GetAllAccountsByRestaurantIdQuery;

import java.util.List;
import java.util.Optional;

public interface AccountQueryService {
    Optional<Account> handle(GetAccountByIdQuery query);
    List<Account> handle(GetAllAccountsByRestaurantIdQuery query);
}
