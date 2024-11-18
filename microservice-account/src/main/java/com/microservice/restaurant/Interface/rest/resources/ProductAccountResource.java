package com.microservice.restaurant.Interface.rest.resources;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

public record ProductAccountResource(
        Long id,
        Long productId,
        Double price,
        Integer quantity,
        Long accountId
) {

}
