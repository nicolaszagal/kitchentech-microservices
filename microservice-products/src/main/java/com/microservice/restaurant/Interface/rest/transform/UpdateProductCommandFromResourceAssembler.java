package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.domain.model.commands.UpdateProductCommand;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommand(
            Long id,
            String productName,
            Double productPrice,
            String productImageUrl,
            String category,
            Long restaurantId
    ){
        return new UpdateProductCommand(id, productName, productPrice, productImageUrl, category, restaurantId);
    }
}
