package com.microservice.restaurant.domain.model.aggregates;

import com.microservice.restaurant.domain.model.commands.CreateTableCommand;
import com.microservice.restaurant.domain.model.valueobjects.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false, length = 50)
    private Long tableNumber;

    @Column(name = "table_capacity", nullable = false, length = 10)
    private Long tableCapacity;

    @Column(name = "table_guests", nullable = false, length = 10)
    private Long tableGuests;

    @Column(name = "table_status", length = 250)
    private Status tableStatus;

    @JoinColumn(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    public RestaurantTable(CreateTableCommand command) {
        this.tableNumber = command.tableNumber();
        this.tableCapacity = command.tableCapacity();
        this.restaurantId = command.restaurantId();
    }
}