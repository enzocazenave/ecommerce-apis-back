package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.repositories.PurchaseOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

    @Autowired
    private PurchaseOrdersRepository purchaseOrderRepository;

    public PurchaseOrder getPurchaseOrder(Long id) {
        return purchaseOrderRepository.findById(id).get();
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
