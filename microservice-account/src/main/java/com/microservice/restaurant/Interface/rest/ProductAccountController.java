package com.microservice.restaurant.Interface.rest;

import com.microservice.restaurant.Interface.rest.resources.AddProductAccountResource;
import com.microservice.restaurant.Interface.rest.resources.ProductAccountResource;
import com.microservice.restaurant.Interface.rest.resources.UpdateProductAccountResource;
import com.microservice.restaurant.Interface.rest.transform.CreateProductAccountCommandFromResourceAssembler;
import com.microservice.restaurant.Interface.rest.transform.ProductAccountResourceFromEntityAssembler;
import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.commands.DeleteProductAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountProduct;
import com.microservice.restaurant.domain.model.queries.GetAllAccountProductsByAccountIdQuery;
import com.microservice.restaurant.domain.services.ProductAccountCommandService;
import com.microservice.restaurant.domain.services.ProductAccountQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/product-account", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product Account", description = "Product Account Management Endpoints")
public class ProductAccountController {
    private final ProductAccountCommandService productAccountCommandService;
    private final ProductAccountQueryService productAccountQueryService;
    public ProductAccountController(ProductAccountCommandService productAccountCommandService, ProductAccountQueryService queryService) {
        this.productAccountCommandService = productAccountCommandService;
        this.productAccountQueryService = queryService;
    }

    @PostMapping
    public ResponseEntity<ProductAccountResource> addProductAccount(@RequestBody AddProductAccountResource addProductAccountResource) {
        Optional<AccountProduct> materialSource = productAccountCommandService
                .handle(CreateProductAccountCommandFromResourceAssembler.toCommand(addProductAccountResource));
        return materialSource.map(source ->
                        new ResponseEntity<>(ProductAccountResourceFromEntityAssembler.toResource(source), CREATED)).
                orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/account/{accountId}")
    private ResponseEntity<List<ProductAccountResource>> getProductAccount(@PathVariable Long accountId) {
        Optional<AccountProduct> accountProductsSource = productAccountQueryService.handle(new GetAllAccountProductsByAccountIdQuery(accountId));
        var accountProductsResource = accountProductsSource.stream()
                .map(ProductAccountResourceFromEntityAssembler::toResource).toList();
        return ResponseEntity.ok(accountProductsResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAccountResource> updateProductAccount(@PathVariable Long id, @RequestBody UpdateProductAccountResource updateProductAccountResource) {
        UpdateAccountProduct command = new UpdateAccountProduct(
                id,
                updateProductAccountResource.productId(),
                updateProductAccountResource.accountId(),
                updateProductAccountResource.price(),
                updateProductAccountResource.quantity()
        );
        Optional<AccountProduct> materialsSource = productAccountCommandService.handle(command);
        return materialsSource.map(source ->
                        new ResponseEntity<>(ProductAccountResourceFromEntityAssembler
                                .toResource(source), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductAccount(@PathVariable Long id) {
        DeleteProductAccountCommand command = new DeleteProductAccountCommand(id);
        productAccountCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
