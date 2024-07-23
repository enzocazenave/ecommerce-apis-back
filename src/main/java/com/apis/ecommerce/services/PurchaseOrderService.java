package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseOrderService {
    public PurchaseOrder getPurchaseOrder(Long id);

    public List<PurchaseOrder> getAllPurchaseOrders();

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
}
