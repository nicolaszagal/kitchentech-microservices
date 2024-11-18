package com.microservice.restaurant.domain.model.commands;

public record DeleteProductCommand(Long id) {
    public DeleteProductCommand {
        if(id == null){
            throw new NullPointerException("id is null");
        }
    }
}
