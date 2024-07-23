package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchasedProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchasedProductsService {
    public PurchasedProduct createPurchasedProduct(PurchasedProduct purchasedProduct);

    public Optional<PurchasedProduct> getPurchasedProductById(Long id);

    public List<PurchasedProduct> getAllPurchasedProducts();
}
