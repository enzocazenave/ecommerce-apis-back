package com.apis.ecommerce.entities;

import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.enums.DiscountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DiscountCoupon {

    public DiscountCoupon() {

    }

    public DiscountCoupon(DiscountCouponRequest discountCouponRequest) {
        this.availableQuantity = discountCouponRequest.getAvailableQuantity();
        this.code = discountCouponRequest.getCode();
        this.status = discountCouponRequest.getStatus();
        this.percentage = discountCouponRequest.getPercentage();
        this.dateCreated = new Date();
        this.lastUpdated = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int availableQuantity;

    @Column
    private String code;

    @Column
    private DiscountStatus status;

    @Column
    private Double percentage;

    @Column
    private Date dateCreated;

    @Column
    private Date lastUpdated;
}
