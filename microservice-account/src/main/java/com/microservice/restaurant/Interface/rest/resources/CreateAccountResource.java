package com.microservice.restaurant.Interface.rest.resources;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;

import java.time.LocalDateTime;
import java.util.List;

public record CreateAccountResource (
        String accountName,
        Long tableId,
        Long restaurantId,
        LocalDateTime dateCreated,
        List<AccountProduct> products
) {
    public CreateAccountResource {
        if (accountName == null || accountName.isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be empty");
        }
        if (tableId == null || tableId < 0) {
            throw new IllegalArgumentException("Table ID cannot be negative");
        }
        if (restaurantId == null || restaurantId < 0) {
            throw new IllegalArgumentException("Restaurant ID cannot be negative");
        }
        if (dateCreated == null || dateCreated.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date cannot be after now");
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be empty");
        }
    }
}
