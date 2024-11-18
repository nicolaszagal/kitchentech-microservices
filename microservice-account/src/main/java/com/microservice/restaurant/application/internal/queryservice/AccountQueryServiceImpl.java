package com.microservice.restaurant.application.internal.queryservice;

import com.microservice.restaurant.domain.model.entities.Account;
import com.microservice.restaurant.domain.model.queries.GetAccountByIdQuery;
import com.microservice.restaurant.domain.model.queries.GetAllAccountsByRestaurantIdQuery;
import com.microservice.restaurant.domain.services.AccountQueryService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    final AccountRepository accountRepository;
    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(GetAccountByIdQuery query) {
        return accountRepository.findById(query.id());
    }

    @Override
    public List<Account> handle(GetAllAccountsByRestaurantIdQuery query) {
        return accountRepository.findAllByRestaurantId(query.restaurantId());
    }
}
