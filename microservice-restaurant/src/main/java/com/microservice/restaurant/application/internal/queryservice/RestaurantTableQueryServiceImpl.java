package com.microservice.restaurant.application.internal.queryservice;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.queries.GetAllTablesByRestaurantIdQuery;
import com.microservice.restaurant.domain.model.queries.GetTableByIdQuery;
import com.microservice.restaurant.domain.services.RestaurantTableQueryService;
import com.microservice.restaurant.infrastructure.persistence.jpa.repository.TableRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableQueryServiceImpl implements RestaurantTableQueryService {
    private final TableRespository tableRespository;
    public RestaurantTableQueryServiceImpl(TableRespository tableRespository) {
        this.tableRespository = tableRespository;
    }

    @Override
    public Optional<RestaurantTable> handle(GetTableByIdQuery query) {
        return tableRespository.findById(query.id());
    }

    @Override
    public List<RestaurantTable> handle(GetAllTablesByRestaurantIdQuery query) {
        return tableRespository.findByRestaurantId(query.restaurantId());
    }
}
