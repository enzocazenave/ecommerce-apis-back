package com.apis.ecommerce.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
    public Product() {}

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
    @JoinColumn(name = "id_category", nullable = false)
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