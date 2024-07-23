package com.apis.ecommerce.entities;

import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class PurchaseOrder {
    public PurchaseOrder() {

    }

    public PurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) {
        this.setUser(purchaseOrderRequest.getUser());
        this.setTotalPrice(purchaseOrderRequest.getTotalPrice());
        this.setStatus(purchaseOrderRequest.getStatus());
        this.setDateCreated(new Date());
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

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchasedProduct> purchasedProducts;

    @ManyToOne
    @JoinColumn(name = "discountCoupon_id", nullable = true)
    private DiscountCoupon discountCoupon;
}
