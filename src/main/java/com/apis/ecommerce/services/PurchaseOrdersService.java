package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseOrdersService {
    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest);

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id);

    public List<PurchaseOrder> getAllPurchaseOrders();

    public PurchaseOrder deletePurchaseOrderById(Long id);

    public PurchaseOrder createPurchaseOrderwithDiscountCode(DiscountCoupon discountCoupon, PurchaseOrderRequest purchaseOrderRequest);

}
