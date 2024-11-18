package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.RestaurantResource;
import com.microservice.restaurant.domain.model.entities.Restaurant;

public class RestaurantResourceFromEntityAssembler {
    public static RestaurantResource toResourceFromEntity(Restaurant restaurant) {
        return new RestaurantResource(
                restaurant.getUsername(),
                restaurant.getName(),
                restaurant.getPassword(),
                restaurant.getPhone(),
                restaurant.getEmail()
        );
    }
}
