package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.entities.Product;
import com.microservice.restaurant.domain.model.queries.GetAllProductsByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsByRestaurantIdQuery query);
}
