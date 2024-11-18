package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.commands.CreateAccountCommand;
import com.microservice.restaurant.domain.model.commands.DeleteAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountCommand;
import com.microservice.restaurant.domain.model.entities.Account;

import java.util.Optional;

public interface AccountCommandService {
    Optional<Account> handle(CreateAccountCommand command);
    Optional<Account> handle(UpdateAccountCommand command);
    void handle(DeleteAccountCommand command);
}
