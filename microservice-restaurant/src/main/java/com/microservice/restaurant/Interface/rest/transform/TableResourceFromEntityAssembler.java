package com.microservice.restaurant.Interface.rest.transform;

import com.microservice.restaurant.Interface.rest.resources.TableResource;
import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;

public class TableResourceFromEntityAssembler {
    public static TableResource toResourceFromEntity(RestaurantTable table) {
        return new TableResource(
                table.getId(),
                table.getTableNumber(),
                table.getTableCapacity(),
                table.getTableGuests(),
                table.getTableStatus(),
                table.getRestaurantId()
        );
    }
}
