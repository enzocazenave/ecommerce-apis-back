package com.apis.ecommerce.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apis.ecommerce.entities.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    List<Category> findByName(String name);
}