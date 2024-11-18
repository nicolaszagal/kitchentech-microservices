package com.microservice.restaurant.Interface.rest.resources;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.valueobjects.State;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateAccountResource (
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
    public UpdateAccountResource {
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if(accountName == null) {
            throw new IllegalArgumentException("accountName cannot be null");
        }
        if(tableId == null) {
            throw new IllegalArgumentException("tableId cannot be null");
        }
        if(restaurantId == null) {
            throw new IllegalArgumentException("restaurantId cannot be null");
        }
        if(state == null) {
            throw new IllegalArgumentException("state cannot be null");
        }
        if(totalAccount == null) {
            throw new IllegalArgumentException("totalAccount cannot be null");
        }
        if(dateCreated == null) {
            throw new IllegalArgumentException("dateCreated cannot be null");
        }
        if(dateLog == null) {
            throw new IllegalArgumentException("dateLog cannot be null");
        }
        if(products == null) {
            throw new IllegalArgumentException("products cannot be null");
        }
    }

}
