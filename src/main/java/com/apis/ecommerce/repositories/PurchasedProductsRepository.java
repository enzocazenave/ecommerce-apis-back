package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchasedProductsRepository extends JpaRepository<PurchasedProduct, Long> {
    @Query("SELECT p FROM PurchasedProduct p WHERE p.purchaseOrder.id = ?1")
    Optional<List<PurchasedProduct>> findByPurchaseOrderId(Long id);
}
