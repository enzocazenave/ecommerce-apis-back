package com.apis.ecommerce.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apis.ecommerce.entities.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {    
    @Query("SELECT c FROM Category c WHERE c.status = true")
    List<Category> findAllActive();

    @Query("SELECT c FROM Category c WHERE c.name = ?1 AND c.status = true")
    List<Category> findByName(String name);

    @Query("SELECT c FROM Category c WHERE c.id = ?1 AND c.status = true")
    Optional<Category> findById(Long id);
}