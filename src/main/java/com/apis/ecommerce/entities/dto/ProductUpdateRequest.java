package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private int stock;
    private double price;
    private String description;
    private String size;
}
