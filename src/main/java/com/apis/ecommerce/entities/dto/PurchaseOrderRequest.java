package com.apis.ecommerce.entities.dto;

import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.enums.PurchaseOrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseOrderRequest {
    private Long id;
    private User user;
    private Double totalPrice;
    private PurchaseOrderStatus Status;
    private Date DateCreated;
}
