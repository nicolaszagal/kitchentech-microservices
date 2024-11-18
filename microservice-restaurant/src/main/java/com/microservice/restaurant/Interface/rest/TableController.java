package com.microservice.restaurant.Interface.rest;

import com.microservice.restaurant.Interface.rest.resources.CreateTableResource;
import com.microservice.restaurant.Interface.rest.resources.TableResource;
import com.microservice.restaurant.Interface.rest.transform.CreateTableCommandFromResourceAssembler;
import com.microservice.restaurant.Interface.rest.transform.TableResourceFromEntityAssembler;
import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.commands.DeleteTableCommand;
import com.microservice.restaurant.domain.model.queries.GetAllTablesByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetTableByIdQuery;
import com.microservice.restaurant.domain.services.RestaurantTableCommandService;
import com.microservice.restaurant.domain.services.RestaurantTableQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/table", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Table", description = "Table Management Endpoints")
public class TableController {
    private final RestaurantTableQueryService restaurantTableQueryService;
    private final RestaurantTableCommandService restaurantTableCommandService;
    public TableController(RestaurantTableQueryService restaurantTableQueryService, RestaurantTableCommandService restaurantTableCommandService) {
        this.restaurantTableQueryService = restaurantTableQueryService;
        this.restaurantTableCommandService = restaurantTableCommandService;
    }

    @PostMapping
    public ResponseEntity<TableResource> createTable(@RequestBody CreateTableResource createTableResource) {
        Optional<RestaurantTable> tableSource = restaurantTableCommandService.handle(CreateTableCommandFromResourceAssembler.toCommand(createTableResource));
        return tableSource.map(s->new ResponseEntity<>(TableResourceFromEntityAssembler.toResourceFromEntity(s), CREATED)).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<TableResource> getTableById(@PathVariable Long id) {
        Optional<RestaurantTable>  tableSource = restaurantTableQueryService.handle(new GetTableByIdQuery(id));
        return tableSource.map(s-> ResponseEntity.ok(TableResourceFromEntityAssembler.toResourceFromEntity(s))).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<TableResource>> getTable(@PathVariable Long restaurantId) {
        List<RestaurantTable> tableSource = restaurantTableQueryService.handle(new GetAllTablesByRestaurantIdQuery(restaurantId));
        var tableResource = tableSource.stream().map(TableResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(tableResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        DeleteTableCommand command = new DeleteTableCommand(id);
        restaurantTableCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

}
