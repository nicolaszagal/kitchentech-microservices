package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.queries.GetAllTablesByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetTableByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableQueryService {
    Optional<RestaurantTable> handle(GetTableByIdQuery query);
    List<RestaurantTable> handle(GetAllTablesByRestaurantIdQuery query);
}
