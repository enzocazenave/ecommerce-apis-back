package com.apis.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.entities.dto.CategoryRequest;
import com.apis.ecommerce.exceptions.CategoryDuplicateException;
import com.apis.ecommerce.services.CategoriesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoriesService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoriesService.getCategoryById(id);
        
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryDuplicateException {
        Category result = categoriesService.createCategory(categoryRequest.getName());
        return ResponseEntity.ok(result);
    }
    
}