package com.microservice.restaurant.Interface.rest.resources;

public record RestaurantResource(
        String username,
        String name,
        String password,
        String phone,
        String email
) {
}
