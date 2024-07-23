package com.apis.ecommerce.services;

import com.apis.ecommerce.entities.PurchasedProduct;
import com.apis.ecommerce.repositories.PurchasedProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasedProductsServiceImpl implements PurchasedProductsService {
    @Autowired
    private PurchasedProductsRepository purchasedProductRepository;

    public PurchasedProduct createPurchasedProduct(PurchasedProduct purchasedProduct) {
        return purchasedProductRepository.save(purchasedProduct);
    }

    public Optional<PurchasedProduct> getPurchasedProductById(Long id) {
        return purchasedProductRepository.findById(id);
    }

    public List<PurchasedProduct> getAllPurchasedProducts() {
        return purchasedProductRepository.findAll();
    }

}
