package com.microservice.restaurant.application.internal.queryservice;

import com.microservice.restaurant.domain.model.entities.Product;
import com.microservice.restaurant.domain.model.queries.GetAllProductsByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetProductByIdQuery;
import com.microservice.restaurant.domain.services.ProductQueryService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }

    @Override
    public List<Product> handle(GetAllProductsByRestaurantIdQuery query) {
        return productRepository.findByRestaurantId(query.restaurantId());
    }
}
