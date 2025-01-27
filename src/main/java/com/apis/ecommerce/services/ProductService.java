package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.apis.ecommerce.exceptions.InsufficientStockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductResponse;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.exceptions.CategoryNonexistentException;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;

@Service
public interface ProductService {
    public List<ProductResponse> getProduct();
    public List<ProductResponse> getProductByIdAndSize(Long id);
    public List<Product> getProductByNameAndSize(String name);
    public Optional<ProductResponse> getProductById(Long id);
    public List<ProductResponse> getProductByName(String nombre);
    public List<ProductResponse> getProductsByCategoryId(Long id);  
    public List<ProductResponse> getProductsByPrice(Double priceMin,Double priceMax );  
    public Product createProduct(ProductRequest p) throws ProductDuplicateException;
    public void deleteProduct(Long id) throws ProductNonexistentException;
    public void updateProduct(Long id, ProductUpdateRequest productRequest) throws ProductNonexistentException, CategoryNonexistentException;
    public void deductStock(Long productId, int quantity) throws ProductNonexistentException, InsufficientStockException;
    public void reduceStockBy(Long id, int quantity) throws ProductNonexistentException, InsufficientStockException;
}