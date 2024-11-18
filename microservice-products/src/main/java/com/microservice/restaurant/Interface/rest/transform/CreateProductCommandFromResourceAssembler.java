package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.CreateProductResource;
import com.microservice.restaurant.domain.model.commands.CreateProductCommand;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommand(CreateProductResource command) {
        return new CreateProductCommand(
                command.productName(),
                command.productPrice(),
                command.productImageUrl(),
                command.category(),
                command.restaurantId()
        );
    }
}
