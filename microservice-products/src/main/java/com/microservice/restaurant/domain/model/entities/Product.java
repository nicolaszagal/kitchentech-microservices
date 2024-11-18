package com.microservice.restaurant.domain.model.entities;

import com.microservice.restaurant.domain.model.commands.CreateProductCommand;
import com.microservice.restaurant.domain.model.commands.UpdateProductCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false, length = 10)
    private Double productPrice;

    @Column(name = "product_image_url", length = 250)
    private String productImageUrl;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @JoinColumn(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    public Product(CreateProductCommand command) {
        this.productName = command.productName();
        this.productPrice = command.productPrice();
        this.productImageUrl = command.productImageUrl();
        this.category = command.category();
        this.restaurantId = command.restaurantId();
    }

    public void updateProduct(UpdateProductCommand command) {
        this.id = command.id();
        this.productName = command.productName();
        this.productPrice = command.productPrice();
        this.productImageUrl = command.productImageUrl();
        this.category = command.category();
        this.restaurantId = command.restaurantId();
    }
}
