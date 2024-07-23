package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchasedProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchasedProductService {
    public PurchasedProduct createPurchasedProduct(PurchasedProduct purchasedProduct);

    public PurchasedProduct getPurchasedProductById(Long id);

    public List<PurchasedProduct> getAllPurchasedProducts();
}
