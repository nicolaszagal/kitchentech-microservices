package com.microservice.restaurant.infrastructure.persistence.jpa.repository;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRespository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByRestaurantId(Long restaurantId);
}
