package com.microservice.restaurant.domain.model.entities;

import com.microservice.restaurant.domain.model.aggregates.RestaurantTable;
import com.microservice.restaurant.domain.model.commands.CreateRestaurantCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @ElementCollection
    @CollectionTable(name = "restaurant_product_ids", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "product_id")
    private List<Long> productIds;

    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RestaurantTable> tableIds;

    @ElementCollection
    @CollectionTable(name = "restaurant_account_ids", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "account_id")
    private List<Long> accountIds;

    public Restaurant(CreateRestaurantCommand command) {
        this.username = command.username();
        this.name = command.name();
        this.password = command.password();
        this.phone = command.phone();
        this.email = command.email();
    }
}