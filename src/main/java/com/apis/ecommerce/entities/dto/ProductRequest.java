package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private Long id_category;
    private String name;
    private int stock;
    private boolean status;
    private double price;
    private String description;
    private String size;

}
