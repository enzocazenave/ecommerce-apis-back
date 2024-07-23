package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int stock;
    private double price;
    private String description;
    private String size;
    private Long idCategory;
}
