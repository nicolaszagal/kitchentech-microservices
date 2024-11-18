package com.microservice.restaurant.domain.model.commands;

public record CreateRestaurantCommand(
        String username,
        String name,
        String password,
        String phone,
        String email
) {
    public CreateRestaurantCommand {
        if(username == null || username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if(phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}
