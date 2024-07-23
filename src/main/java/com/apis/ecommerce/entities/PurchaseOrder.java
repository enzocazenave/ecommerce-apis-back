package com.apis.ecommerce.entities;

import com.apis.ecommerce.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Double totalPrice;

    @Column
    private PurchaseOrderStatus Status;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchasedProduct> purchasedProducts;

    @Column
    private Date DateCreated;

}
