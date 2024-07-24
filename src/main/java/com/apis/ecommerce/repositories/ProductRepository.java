package com.apis.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apis.ecommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    @Query("SELECT c FROM Product c WHERE c.name = ?1")
    List<Product> findByName(String name);

    @Query("SELECT c FROM Product c WHERE c.price >= ?1 AND c.price <= ?2")
    List<Product> findByPrice(double priceMin, double priceMax);

    @Query("SELECT c FROM Product c WHERE c.stock >= 0")
    List<Product> findAllAvailable();

    @Query("UPDATE Product c SET c.stock = -1  WHERE c.id = ?1")
    List<Product> removedLogical(Long id);
    
    @Query("SELECT c FROM Product c WHERE c.category.id = ?1")
    List<Product> findByCategoryId(Long id);
}
