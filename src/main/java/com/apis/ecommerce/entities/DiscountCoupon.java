package com.apis.ecommerce.entities;

import com.apis.ecommerce.entities.dto.DiscountCouponRequest;
import com.apis.ecommerce.enums.DiscountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class DiscountCoupon {

    public DiscountCoupon() {

    }

    public DiscountCoupon(DiscountCouponRequest discountCouponRequest) {
        this.count = discountCouponRequest.getCount();
        this.code = discountCouponRequest.getCode();
        this.status = discountCouponRequest.getStatus();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int count;

    @Column
    private String code;

    @Column
    private DiscountStatus status;

    @Column
    private Double percentage;

    @Column
    private Date dateCreated;

    @OneToMany(mappedBy = "discountCoupon")
    private List<PurchasedProduct> purchasedProduct;
}
