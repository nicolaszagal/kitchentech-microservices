package com.microservice.restaurant.application.internal.commandservice;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.commands.AddAccountProduct;
import com.microservice.restaurant.domain.model.commands.DeleteProductAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountProduct;
import com.microservice.restaurant.domain.services.ProductAccountCommandService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.AccountProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductAccountCommandServiceImpl implements ProductAccountCommandService {
    private final AccountProductRepository accountProductRepository;
    public ProductAccountCommandServiceImpl(AccountProductRepository accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public Optional<AccountProduct> handle(AddAccountProduct command) {
        var accountProduct = new AccountProduct();
        var createdAccountProduct = accountProductRepository.save(accountProduct);
        return Optional.of(createdAccountProduct);
    }

    @Override
    public Optional<AccountProduct> handle(UpdateAccountProduct command) {
        var result = accountProductRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Account product not found");

        var accountToUpdate = result.get();
        try {
            accountToUpdate.updateAccountProduct(command);
            var updateAccount = accountProductRepository.save(accountToUpdate);
            return Optional.of(updateAccount);
        } catch (Exception e) {
            throw new IllegalArgumentException("Account product not found");
        }
    }

    @Override
    public void handle(DeleteProductAccountCommand command) {
        if (!accountProductRepository.existsById(command.id())) throw new IllegalArgumentException("Account not found");
        try {
            accountProductRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting account product");
        }
    }
}
