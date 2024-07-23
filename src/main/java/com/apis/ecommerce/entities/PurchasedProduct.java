package com.apis.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Long productId;

    @Column
    private int unit;

    @Column
    private Double price;

    @Column
    private Date DateCreated;

    @ManyToOne
    @JoinColumn(name = "purchaseorder_id", nullable = false)
    private PurchaseOrder purchaseOrder;
}
