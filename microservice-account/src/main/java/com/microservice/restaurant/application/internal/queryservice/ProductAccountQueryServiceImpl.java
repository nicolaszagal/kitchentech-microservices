package com.microservice.restaurant.application.internal.queryservice;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.queries.GetAllAccountProductsByAccountIdQuery;
import com.microservice.restaurant.domain.services.ProductAccountQueryService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.AccountProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductAccountQueryServiceImpl implements ProductAccountQueryService {
    private final AccountProductRepository accountProductRepository;
    public ProductAccountQueryServiceImpl(AccountProductRepository accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public Optional<AccountProduct> handle(GetAllAccountProductsByAccountIdQuery query) {
        return accountProductRepository.findAllAccountProductsByAccountId(query.accountId());
    }
}
