package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.exceptions.CategoryNonexistentException;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;

@Service
public interface ProductService {
    public List<Product> getProduct();
    public Optional<Product> getProductById(Long id);
    public List<Product> getProductsByCategoryId(Long id);  
    public Product createProduct(ProductRequest p) throws ProductDuplicateException;
    public void deleteProduct(Long id) throws ProductNonexistentException;
    public void updateProduct(ProductUpdateRequest productRequest) throws ProductNonexistentException, CategoryNonexistentException;
} 