package com.microservice.restaurant.domain.model.commands;

public record DeleteTableCommand(Long id) {
    public DeleteTableCommand {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
