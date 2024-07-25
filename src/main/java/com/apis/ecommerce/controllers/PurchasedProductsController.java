package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.services.PurchasedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/purchase_products")
public class PurchasedProductsController {
    @Autowired
    private PurchasedProductsService purchasedProductService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchasedProduct> getPurchasedProductById(@PathVariable Long id) {

        Optional<PurchasedProduct> purchasedProduct = purchasedProductService.getPurchasedProductById(id);
        if (purchasedProduct.isPresent()) {
            return ResponseEntity.ok(purchasedProduct.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<PurchasedProduct> getAllPurchasedProducts() {
        return purchasedProductService.getAllPurchasedProducts();
    }

    @GetMapping("/purchases_orders/{id}")
    public List<PurchasedProduct> getAllPurchasedProductsByPurchaseorderId(@PathVariable Long id) {
        return purchasedProductService.getPurchasedProductByPurchaseOrderId(id);
    }

}
