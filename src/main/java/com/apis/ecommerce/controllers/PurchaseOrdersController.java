package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.entities.dto.PurchasedProductRequest;
import com.apis.ecommerce.exceptions.*;
import com.apis.ecommerce.services.DiscountCouponsService;
import com.apis.ecommerce.services.PurchaseOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/purchase_orders")
public class PurchaseOrdersController {
    @Autowired
    private PurchaseOrdersService purchaseOrderService;
    @Autowired
    private DiscountCouponsService discountCouponsService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(Long id) {

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        if (purchaseOrder.isPresent()) {
            return ResponseEntity.ok(purchaseOrder.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) throws InvalidUnitsException, InsufficientStockException, ProductNonexistentException, UserNotFoundException {
        for (PurchasedProductRequest purchasedProductRequest : purchaseOrderRequest.getPurchasedProductRequests()) {
            if (purchasedProductRequest.getUnits() <= 0) {
                throw new InvalidUnitsException();
            }
        }

        String discountCode = purchaseOrderRequest.getDiscountCode();
        if (discountCode != null && !discountCode.isEmpty()) {
            Boolean hasCoupon = discountCouponsService.hasCuopons(purchaseOrderRequest.getDiscountCode());
            if (!hasCoupon) {
                return ResponseEntity.badRequest().build();
            }
        }

        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderRequest);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

}
