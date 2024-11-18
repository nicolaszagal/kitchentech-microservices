package com.microservice.restaurant.application.internal.commandservice;

import com.microservice.restaurant.domain.model.commands.CreateProductCommand;
import com.microservice.restaurant.domain.model.commands.DeleteProductCommand;
import com.microservice.restaurant.domain.model.commands.UpdateProductCommand;
import com.microservice.restaurant.domain.model.entities.Product;
import com.microservice.restaurant.domain.services.ProductCommandService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;
    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        var product = new Product(command);
        var createdProduct = productRepository.save(product);
        return Optional.of(createdProduct);
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        var result = productRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Product not found");

        var product = result.get();
        product.updateProduct(command);
        var updatedProduct = productRepository.save(product);
        return Optional.of(updatedProduct);
    }

    @Override
    public void handle(DeleteProductCommand command) {

    }
}
