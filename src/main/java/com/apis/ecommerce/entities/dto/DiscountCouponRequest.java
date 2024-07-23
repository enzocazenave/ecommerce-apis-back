package com.apis.ecommerce.entities.dto;

import com.apis.ecommerce.enums.DiscountStatus;
import lombok.Data;

@Data
public class DiscountCouponRequest {
    private Integer count;
    private String code;
    private DiscountStatus status;
    private Double percentage;
}
