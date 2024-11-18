package com.microservice.restaurant.domain.model.aggregates;

import com.microservice.restaurant.domain.model.commands.UpdateAccountProduct;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account_products")
public class AccountProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "products_id", nullable = false)
    private Long productId;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @JoinColumn(name = "account_id", nullable = false)
    private Long accountId;

    public void updateAccountProduct(UpdateAccountProduct command) {
        this.id = command.id();
        this.productId = command.productId();
        this.price = command.price();
        this.quantity = command.quantity();
        this.accountId = command.accountId();
    }
}
