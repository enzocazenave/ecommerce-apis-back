package com.apis.ecommerce.entities.dto;

import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.enums.PurchaseOrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PurchaseOrderRequest {
    private Long userId;
    private List<PurchasedProductRequest> purchasedProductRequests;
}
