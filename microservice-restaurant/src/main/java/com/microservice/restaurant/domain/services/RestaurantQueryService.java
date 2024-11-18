package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.entities.Restaurant;
import com.microservice.restaurant.domain.model.queries.GetAllRestaurantsQuery;
import com.microservice.restaurant.domain.model.queries.GetRestaurantByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> handle(GetRestaurantByIdQuery query);
    List<Restaurant> handle(GetAllRestaurantsQuery query);
}
