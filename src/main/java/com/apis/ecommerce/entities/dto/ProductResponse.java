package com.apis.ecommerce.entities.dto;

import java.util.List;

import com.apis.ecommerce.entities.ProductImages;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private int stock;
    private double price;
    private String description;
    private String size;
    private Long idCategory;
    private List<ProductImages> images;   
}
