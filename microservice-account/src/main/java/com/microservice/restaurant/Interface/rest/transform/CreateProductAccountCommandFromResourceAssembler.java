package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.AddProductAccountResource;
import com.microservice.restaurant.domain.model.commands.AddAccountProduct;

public class CreateProductAccountCommandFromResourceAssembler {
    public static AddAccountProduct toCommand(AddProductAccountResource command) {
        return new AddAccountProduct(
                command.productId(),
                command.accountId()
        );
    }
}
