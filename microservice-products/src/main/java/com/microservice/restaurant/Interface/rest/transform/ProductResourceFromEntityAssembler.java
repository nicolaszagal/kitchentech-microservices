package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.ProductResource;
import com.microservice.restaurant.domain.model.entities.Product;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(
                product.getProductName(),
                product.getProductPrice(),
                product.getProductImageUrl(),
                product.getCategory(),
                product.getRestaurantId()
        );
    }
}
