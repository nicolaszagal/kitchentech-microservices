package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.AccountResource;
import com.microservice.restaurant.Interface.rest.resources.ProductAccountResource;
import com.microservice.restaurant.domain.model.aggregates.AccountProduct;

public class ProductAccountResourceFromEntityAssembler {
    public static ProductAccountResource toResource(AccountProduct accountProduct) {
        return new ProductAccountResource(
                accountProduct.getId(),
                accountProduct.getProductId(),
                accountProduct.getPrice(),
                accountProduct.getQuantity(),
                accountProduct.getAccountId()
        );
    }
}
