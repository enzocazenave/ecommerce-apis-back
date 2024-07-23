package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.repositories.PurchaseOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

    @Autowired
    private PurchaseOrdersRepository purchaseOrderRepository;

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
