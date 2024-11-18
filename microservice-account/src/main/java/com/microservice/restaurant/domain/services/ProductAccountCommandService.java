package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.commands.AddAccountProduct;
import com.microservice.restaurant.domain.model.commands.DeleteProductAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountProduct;

import java.util.Optional;

public interface ProductAccountCommandService {
    Optional<AccountProduct> handle(AddAccountProduct command);
    Optional<AccountProduct> handle(UpdateAccountProduct command);
    void handle(DeleteProductAccountCommand command);
}
