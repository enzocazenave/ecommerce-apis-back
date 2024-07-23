package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class PurchasedProductRequest {
    private Long productId;
    private Double priceGross;
    private Double priceNet;
    private int unit;
}
