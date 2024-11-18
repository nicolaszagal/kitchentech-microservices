package com.microservice.restaurant.domain.model.entities;

import com.microservice.restaurant.domain.model.aggregates.AccountProduct;
import com.microservice.restaurant.domain.model.commands.CreateAccountCommand;
import com.microservice.restaurant.domain.model.commands.UpdateAccountCommand;
import com.microservice.restaurant.domain.model.valueobjects.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @JoinColumn(name = "table_id")
    private Long tableId;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name="state",nullable = false)
    private State state;

    @Column(name = "total_account",nullable = false)
    private Float totalAccount;

    @Column(name = "date_created",nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_log",nullable = false)
    private LocalDateTime dateLog;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AccountProduct> products = new ArrayList<>();

    public Account(CreateAccountCommand command) {
        this.accountName = command.accountName();
        this.tableId = command.tableId();
        this.restaurantId = command.restaurantId();
        this.dateCreated = LocalDateTime.now();
        this.products = new ArrayList<>();
    }

    public void updateTotalAccount() {
        this.totalAccount = (float) products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }

    public void updateAccount(UpdateAccountCommand command) {
        this.id = command.id();
        this.accountName = command.accountName();
        this.tableId = command.tableId();
        this.restaurantId = command.restaurantId();
        this.state = command.state();
        this.totalAccount = command.totalAccount();
        this.dateCreated = LocalDateTime.now();
        this.dateLog = LocalDateTime.now();
        this.products = new ArrayList<>();
    }
}