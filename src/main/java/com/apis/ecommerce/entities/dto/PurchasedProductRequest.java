package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class PurchasedProductRequest {
    private Long productId;
    private Double price;
    private int unit;
}
