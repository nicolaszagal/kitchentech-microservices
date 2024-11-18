package com.microservice.restaurant.Interface.rest;

import com.microservice.restaurant.Interface.rest.resources.CreateProductResource;
import com.microservice.restaurant.Interface.rest.resources.ProductResource;
import com.microservice.restaurant.Interface.rest.resources.UpdateProductResource;
import com.microservice.restaurant.Interface.rest.transform.CreateProductCommandFromResourceAssembler;
import com.microservice.restaurant.Interface.rest.transform.ProductResourceFromEntityAssembler;
import com.microservice.restaurant.domain.model.commands.DeleteProductCommand;
import com.microservice.restaurant.domain.model.commands.UpdateProductCommand;
import com.microservice.restaurant.domain.model.entities.Product;
import com.microservice.restaurant.domain.model.queries.GetAllProductsByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetProductByIdQuery;
import com.microservice.restaurant.domain.services.ProductCommandService;
import com.microservice.restaurant.domain.services.ProductQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Product Management Endpoints")
public class ProductController {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;
    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        Optional<Product> productSource = productCommandService.handle(CreateProductCommandFromResourceAssembler.toCommand(createProductResource));
        return productSource.map(s-> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(s), CREATED)).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long id, @RequestBody UpdateProductResource updateProductResource) {
        UpdateProductCommand command = new UpdateProductCommand(
                id,
                updateProductResource.productName(),
                updateProductResource.productPrice(),
                updateProductResource.productImageUrl(),
                updateProductResource.category(),
                updateProductResource.restaurantId()
        );
        Optional<Product> productSource = productCommandService.handle(command);
        return productSource.map(s-> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(s), CREATED)).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResource> deleteProduct(@PathVariable Long id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        productCommandService.handle(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        Optional<Product> productSource = productQueryService.handle(new GetProductByIdQuery(id));
        return productSource.map(s-> ResponseEntity.ok(ProductResourceFromEntityAssembler.toResourceFromEntity(s))).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ProductResource>> getProductByRestaurantId(@PathVariable Long restaurantId) {
        List<Product> productSource = productQueryService.handle(new GetAllProductsByRestaurantIdQuery(restaurantId));
        var productResource = productSource.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResource);
    }
}
