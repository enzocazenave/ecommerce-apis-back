package com.apis.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double priceGross;

    @Column
    private Double priceNet;

    @Column
    private Double discountAmount;

    @Column
    private int unit;

    @Column
    private Date DateCreated;

    @ManyToOne
    @JoinColumn(name = "purchaseorder_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "discountCoupon_id", nullable = false)
    private DiscountCoupon discountCoupon;
}
