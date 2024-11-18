package com.microservice.restaurant.infrastructure.persistence.jpa.repository;

import com.microservice.restaurant.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByRestaurantId(Long restaurantId);
    List<Product> findByCategory(String Category);
}
