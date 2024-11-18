package com.microservice.restaurant.domain.model.commands;

public record DeleteProductAccountCommand(Long id) {
    public DeleteProductAccountCommand {
        if (id == null) throw new NullPointerException("id is null");
    }
}
