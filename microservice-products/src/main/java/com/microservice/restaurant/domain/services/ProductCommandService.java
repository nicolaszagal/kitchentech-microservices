package com.microservice.restaurant.domain.services;

import com.microservice.restaurant.domain.model.commands.CreateProductCommand;
import com.microservice.restaurant.domain.model.commands.DeleteProductCommand;
import com.microservice.restaurant.domain.model.commands.UpdateProductCommand;
import com.microservice.restaurant.domain.model.entities.Product;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}
