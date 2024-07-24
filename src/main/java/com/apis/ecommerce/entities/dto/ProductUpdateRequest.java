package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private Integer stock;
    private Double price;
    private String description;
    private String size;
    private Long idCategory;
}
