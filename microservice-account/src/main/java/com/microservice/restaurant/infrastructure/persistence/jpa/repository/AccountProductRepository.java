package com.microservice.restaurant.infrastructure.persistence.jpa.repository;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountProductRepository extends JpaRepository<AccountProduct, Long> {
    Optional<AccountProduct> findAllAccountProductsByAccountId(Long accountId);
}