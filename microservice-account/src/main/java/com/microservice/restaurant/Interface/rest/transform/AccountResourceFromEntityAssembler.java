package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.AccountResource;
import com.microservice.restaurant.domain.model.entities.Account;

public class AccountResourceFromEntityAssembler {
    public static AccountResource toResource(Account account) {
        return new AccountResource(
                account.getId(),
                account.getAccountName(),
                account.getTableId(),
                account.getRestaurantId(),
                account.getState(),
                account.getTotalAccount(),
                account.getDateCreated(),
                account.getDateLog(),
                account.getProducts()
        );
    }
}
