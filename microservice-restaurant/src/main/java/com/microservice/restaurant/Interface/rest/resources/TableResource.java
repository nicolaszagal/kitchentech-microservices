package com.microservice.restaurant.Interface.rest.resources;

import com.microservice.restaurant.domain.model.valueobjects.Status;

public record TableResource(
        Long id,
        Long tableNumber,
        Long tableCapacity,
        Long tableGuests,
        Status tableStatus,
        Long restaurantId
) {
}
