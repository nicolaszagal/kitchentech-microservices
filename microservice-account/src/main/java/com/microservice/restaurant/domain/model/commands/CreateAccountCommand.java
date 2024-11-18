package com.microservice.restaurant.domain.model.commands;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.valueobjects.State;

import java.time.LocalDateTime;
import java.util.List;

public record CreateAccountCommand(
        String accountName,
        Long tableId,
        Long restaurantId,
        LocalDateTime dateCreated,
        List<AccountProduct> products
) {
    public CreateAccountCommand {
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
