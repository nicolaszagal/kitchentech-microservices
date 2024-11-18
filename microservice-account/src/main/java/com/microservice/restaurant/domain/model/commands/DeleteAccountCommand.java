package com.microservice.restaurant.domain.model.commands;

public record DeleteAccountCommand(Long id) {
    public DeleteAccountCommand {
        if(id == null){
            throw new NullPointerException("id is null");
        }
    }
}
