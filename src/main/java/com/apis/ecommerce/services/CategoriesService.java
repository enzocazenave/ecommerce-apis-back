package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.exceptions.CategoryDuplicateException;

@Service
public interface CategoriesService {
    public List<Category> getCategories();

    public Optional<Category> getCategoryById(Long id);

    public Category createCategory(String name) throws CategoryDuplicateException;
}