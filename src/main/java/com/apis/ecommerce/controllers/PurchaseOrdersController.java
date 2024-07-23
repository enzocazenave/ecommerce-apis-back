package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.DiscountCoupon;
import com.apis.ecommerce.entities.PurchaseOrder;
import com.apis.ecommerce.entities.dto.PurchaseOrderRequest;
import com.apis.ecommerce.services.PurchaseOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase_orders")
public class PurchaseOrdersController {
    @Autowired
    private PurchaseOrdersService purchaseOrderService;

    @Autowired
    private DiscountCouponsController discountCouponsController;


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

    @PostMapping("/code/{discountCode}")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest, @PathVariable String discountCode ) {

        PurchaseOrder createdPurchaseOrder;

        if (discountCode != null && !discountCode.isEmpty()) {
            ResponseEntity<List<DiscountCoupon>> discountCoupons = discountCouponsController.getDiscountCouponByDiscountCode(discountCode);

            //TODO Al usar el cupon debo descontar ese uso

            //TODO select some discount coupon
            DiscountCoupon discountCoupon = discountCoupons.getBody().getFirst();
            purchaseOrderService.createPurchaseOrderwithDiscountCode(discountCoupon, purchaseOrderRequest);
        }
        createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderRequest);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

}
