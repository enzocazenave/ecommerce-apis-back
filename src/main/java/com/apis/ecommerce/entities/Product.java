package com.apis.ecommerce.entities;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Product {
    public Product() {
    }

    public Product(String name, int stock, double price, String description, String size) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.size = size;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String name;

    @Column
    private int stock;

    @Column
    private boolean status = true;

    @Column
    private double price;

    @Column
    private String description;

    @Column
    private String size;
}