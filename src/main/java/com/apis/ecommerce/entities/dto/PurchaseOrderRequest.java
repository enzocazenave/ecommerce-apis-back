package com.apis.ecommerce.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderRequest {
    private Long userId;
    private List<PurchasedProductRequest> purchasedProductRequests;
    private String discountCode;
}
