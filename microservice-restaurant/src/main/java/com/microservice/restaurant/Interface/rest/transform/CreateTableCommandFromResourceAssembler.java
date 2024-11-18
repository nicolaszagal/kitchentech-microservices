package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.CreateTableResource;
import com.microservice.restaurant.domain.model.commands.CreateTableCommand;

public class CreateTableCommandFromResourceAssembler {
    public static CreateTableCommand toCommand(CreateTableResource command) {
        return new CreateTableCommand(
                command.tableNumber(),
                command.tableCapacity(),
                command.restaurantId()
        );
    }
}
