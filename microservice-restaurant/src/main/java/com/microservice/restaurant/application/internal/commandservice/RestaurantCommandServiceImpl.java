package com.microservice.restaurant.application.internal.commandservice;

import com.microservice.restaurant.domain.model.commands.CreateRestaurantCommand;
import com.microservice.restaurant.domain.model.entities.Restaurant;
import com.microservice.restaurant.domain.services.RestaurantCommandService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    public RestaurantCommandServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> handle(CreateRestaurantCommand command) {
        var restaurant = new Restaurant(command);
        var createdRestaurant = restaurantRepository.save(restaurant);
        return Optional.of(createdRestaurant);
    }
}
