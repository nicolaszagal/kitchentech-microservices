package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.commands.CreateRestaurantCommand;
import com.microservice.restaurant.domain.model.entities.Restaurant;

import java.util.Optional;

public interface RestaurantCommandService {
    Optional<Restaurant> handle(CreateRestaurantCommand command);
}
