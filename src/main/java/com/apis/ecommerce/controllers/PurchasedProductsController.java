package com.apis.ecommerce.controllers;

import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.services.PurchasedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase_products")
public class PurchasedProductsController {
    @Autowired
    private PurchasedProductsService purchasedProductService;

    @GetMapping("/{id}")
    public PurchasedProduct getPurchasedProductById(Long id) {
        return purchasedProductService.getPurchasedProductById(id);
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
