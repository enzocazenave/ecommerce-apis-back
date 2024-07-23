package com.apis.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchaseorder_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column
    private Long productId;

    @Column
    private Double priceGross;

    @Column
    private Double priceNet;

    @Column
    private int unit;

    @ManyToOne
    @JoinColumn(name = "discountcoupon_id")
    private DiscountCoupon discountCoupon;
}
