package com.apis.ecommerce.entities;

import com.apis.ecommerce.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PurchaseOrder {
    public PurchaseOrder() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double totalPrice;

    @Column
    private PurchaseOrderStatus Status;

    @Column
    private Date DateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "discountCoupon_id", nullable = true)
    private DiscountCoupon discountCoupon;
}
