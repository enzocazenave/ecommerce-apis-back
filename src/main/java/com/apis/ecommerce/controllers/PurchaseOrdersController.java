package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.services.PurchaseOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase_orders")
public class PurchaseOrdersController {
    @Autowired
    private PurchaseOrdersService purchaseOrderService;

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderService.getPurchaseOrder(id);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

}
