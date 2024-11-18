package com.microservice.restaurant.application.internal.commandservice;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.commands.CreateTableCommand;
import com.microservice.restaurant.domain.model.commands.DeleteTableCommand;
import com.microservice.restaurant.domain.services.RestaurantTableCommandService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.TableRespository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantTableCommandServiceImpl implements RestaurantTableCommandService {
    private final TableRespository tableRespository;
    public RestaurantTableCommandServiceImpl(TableRespository tableRespository) {
        this.tableRespository = tableRespository;
    }

    @Override
    public Optional<RestaurantTable> handle(CreateTableCommand command) {
        var restaurantTable = new RestaurantTable(command);
        tableRespository.save(restaurantTable);
        return Optional.of(restaurantTable);
    }

    @Override
    public void handle(DeleteTableCommand command) {
        if(!tableRespository.existsById(command.id())) throw new IllegalArgumentException("Table not found");
        try {
            tableRespository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting table");
        }

    }
}
