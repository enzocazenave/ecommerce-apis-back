package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductsRepository extends JpaRepository<PurchasedProduct, Long> {
}
