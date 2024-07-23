package com.apis.ecommerce.repositories;

import com.apis.ecommerce.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    @Query("SELECT c FROM ProductImages c WHERE c.product.id = ?1")
    List<ProductImages> findByProductId(Long productId);
}
