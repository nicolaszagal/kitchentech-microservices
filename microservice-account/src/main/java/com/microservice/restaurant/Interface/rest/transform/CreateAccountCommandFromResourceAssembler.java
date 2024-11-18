package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.CreateAccountResource;
import com.microservice.restaurant.domain.model.commands.CreateAccountCommand;

public class CreateAccountCommandFromResourceAssembler {
    public static CreateAccountCommand toCommand(CreateAccountResource command) {
        return new CreateAccountCommand(
                command.accountName(),
                command.tableId(),
                command.restaurantId(),
                command.dateCreated(),
                command.products()
        );
    }
}
