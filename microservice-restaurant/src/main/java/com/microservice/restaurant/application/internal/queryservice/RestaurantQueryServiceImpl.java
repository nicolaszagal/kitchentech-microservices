package com.microservice.restaurant.application.internal.queryservice;

import com.microservice.restaurant.domain.model.entities.Restaurant;
import com.microservice.restaurant.domain.model.queries.GetAllRestaurantsQuery;
import com.microservice.restaurant.domain.model.queries.GetRestaurantByIdQuery;
import com.microservice.restaurant.domain.services.RestaurantQueryService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantQueryServiceImpl implements RestaurantQueryService {
    private final RestaurantRepository restaurantRepository;
    public RestaurantQueryServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> handle(GetRestaurantByIdQuery query) {
        return restaurantRepository.findById(query.id());
    }

    @Override
    public List<Restaurant> handle(GetAllRestaurantsQuery query) {
        return restaurantRepository.findAll();
    }
}
