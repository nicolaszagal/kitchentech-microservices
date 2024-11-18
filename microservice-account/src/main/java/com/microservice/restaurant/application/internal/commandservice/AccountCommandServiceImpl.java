package com.microservice.restaurant.application.internal.commandservice;

import com.microservice.restaurant.domain.model.commands.CreateAccountCommand;
import com.microservice.restaurant.domain.model.commands.DeleteAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountCommand;
import com.microservice.restaurant.domain.model.entities.Account;
import com.microservice.restaurant.domain.services.AccountCommandService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {
    final AccountRepository accountRepository;
    public AccountCommandServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(CreateAccountCommand command) {
        var account = new Account(command);
        var createdAccount = accountRepository.save(account);
        return Optional.of(createdAccount);
    }

    @Override
    public Optional<Account> handle(UpdateAccountCommand command) {
        var result = accountRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Account not found");

        var accountToUpdate = result.get();
        try {
            accountToUpdate.updateAccount(command);
            var updateAccount = accountRepository.save(accountToUpdate);
            return Optional.of(updateAccount);
        } catch (Exception e) {
            throw new IllegalArgumentException("Material not found");
        }
    }

    @Override
    public void handle(DeleteAccountCommand command) {
        if (!accountRepository.existsById(command.id())) throw new IllegalArgumentException("Account not found");
        try {
            accountRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting account");
        }
    }
}
