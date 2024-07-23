package com.apis.ecommerce.entities;

import com.apis.ecommerce.enums.DiscountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DiscountCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "discountCoupon")
    private List<PurchasedProduct> purchasedProducts;

    @Column
    private int count;

    @Column
    private String code;

    @Column
    private DiscountStatus status;
}
