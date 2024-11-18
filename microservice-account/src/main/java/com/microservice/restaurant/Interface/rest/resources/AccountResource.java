package com.microservice.restaurant.Interface.rest.resources;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.valueobjects.State;

import java.time.LocalDateTime;
import java.util.List;

public record AccountResource (
    Long id,
    String accountName,
    Long tableId,
    Long restaurantId,
    State state,
    Float totalAccount,
    LocalDateTime dateCreated,
    LocalDateTime dateLog,
    List<AccountProduct> products
) {

}
