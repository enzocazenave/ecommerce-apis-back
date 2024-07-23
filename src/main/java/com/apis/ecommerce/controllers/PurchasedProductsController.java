package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.services.PurchasedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase_products")
public class PurchasedProductsController {
    @Autowired
    private PurchasedProductsService purchasedProductService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchasedProduct> getPurchasedProductById(Long id) {

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

    @PostMapping
    public PurchasedProduct createPurchasedProduct(PurchasedProduct purchasedProduct) {
        return purchasedProductService.createPurchasedProduct(purchasedProduct);
    }
}
