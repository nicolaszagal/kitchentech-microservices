package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.commands.CreateTableCommand;
import com.microservice.restaurant.domain.model.commands.DeleteTableCommand;

import java.util.Optional;

public interface RestaurantTableCommandService {
    Optional<RestaurantTable> handle(CreateTableCommand command);
    void handle(DeleteTableCommand command);
}
