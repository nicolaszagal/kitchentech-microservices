package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.CreateRestaurantResource;
import com.microservice.restaurant.domain.model.commands.CreateRestaurantCommand;

public class CreateRestaurantCommandFromResourceAssembler {
    public static CreateRestaurantCommand toCommand(CreateRestaurantResource command) {
        return new CreateRestaurantCommand(
                command.username(),
                command.name(),
                command.password(),
                command.phone(),
                command.email()
        );
    }
}
