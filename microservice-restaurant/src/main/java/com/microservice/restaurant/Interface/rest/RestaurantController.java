package com.microservice.restaurant.Interface.rest;

import com.microservice.restaurant.Interface.rest.resources.CreateRestaurantResource;
import com.microservice.restaurant.Interface.rest.resources.RestaurantResource;
import com.microservice.restaurant.Interface.rest.transform.CreateRestaurantCommandFromResourceAssembler;
import com.microservice.restaurant.Interface.rest.transform.RestaurantResourceFromEntityAssembler;
import com.microservice.restaurant.domain.model.entities.Restaurant;
import com.microservice.restaurant.domain.model.queries.GetAllRestaurantsQuery;
import com.microservice.restaurant.domain.model.queries.GetRestaurantByIdQuery;
import com.microservice.restaurant.domain.services.RestaurantCommandService;
import com.microservice.restaurant.domain.services.RestaurantQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Restaurant", description = "Restaurant Management Endpoints")
public class RestaurantController {
    private final RestaurantQueryService restaurantQueryService;
    private final RestaurantCommandService restaurantCommandService;
    public RestaurantController(RestaurantQueryService productQueryService, RestaurantCommandService productCommandService) {
        this.restaurantQueryService = productQueryService;
        this.restaurantCommandService = productCommandService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResource> createRestaurant(@RequestBody CreateRestaurantResource createRestaurantResource) {
        Optional<Restaurant> restaurantSource = restaurantCommandService.handle(CreateRestaurantCommandFromResourceAssembler.toCommand(createRestaurantResource));
        return restaurantSource.map(s-> new ResponseEntity<>(RestaurantResourceFromEntityAssembler.toResourceFromEntity(s), CREATED)).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResource> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurantSource = restaurantQueryService.handle(new GetRestaurantByIdQuery(id));
        return restaurantSource.map(s-> ResponseEntity.ok(RestaurantResourceFromEntityAssembler.toResourceFromEntity(s))).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantResource>> getAllRestaurants() {
        List<Restaurant> restaurantSource = restaurantQueryService.handle(new GetAllRestaurantsQuery());
        var restaurantResource = restaurantSource.stream().map(RestaurantResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(restaurantResource);
    }
}
