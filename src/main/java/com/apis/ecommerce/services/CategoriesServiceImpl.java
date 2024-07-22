package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.exceptions.CategoryDuplicateException;
import com.apis.ecommerce.repositories.CategoriesRepository;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        System.out.println(id);
        return categoriesRepository.findById(id);
    }

    public Category createCategory(String name) throws CategoryDuplicateException {
        if (!categoriesRepository.findByName(name).isEmpty()) {
            throw new CategoryDuplicateException();
        }

        Category category = new Category(name);
        return categoriesRepository.save(category);
    }
}