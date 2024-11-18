package com.microservice.restaurant.infrastructure.persistence.jpa.repository;

import com.microservice.restaurant.domain.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByRestaurantId(Long restaurantId);
}