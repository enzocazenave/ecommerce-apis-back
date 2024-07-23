package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseOrdersService {
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id);

    public List<PurchaseOrder> getAllPurchaseOrders();

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
}
