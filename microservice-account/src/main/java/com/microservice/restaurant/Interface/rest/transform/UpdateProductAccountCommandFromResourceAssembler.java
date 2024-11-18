package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.domain.model.commands.UpdateAccountProduct;

public class UpdateProductAccountCommandFromResourceAssembler {
    public static UpdateAccountProduct toCommand (
            Long id,
            Long productId,
            Long accountId,
            Double price,
            Integer quantity
    ) {
      return new UpdateAccountProduct(id, productId, accountId, price, quantity);
    }
}
