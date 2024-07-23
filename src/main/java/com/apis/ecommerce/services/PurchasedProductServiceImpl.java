package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.repositories.PurchasedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedProductServiceImpl implements PurchasedProductService {
    @Autowired
    private PurchasedProductRepository purchasedProductRepository;

    public PurchasedProduct createPurchasedProduct(PurchasedProduct purchasedProduct) {
        return purchasedProductRepository.save(purchasedProduct);
    }

    public PurchasedProduct getPurchasedProductById(Long id) {
        return purchasedProductRepository.findById(id).get();
    }

    public List<PurchasedProduct> getAllPurchasedProducts() {
        return purchasedProductRepository.findAll();
    }
}
