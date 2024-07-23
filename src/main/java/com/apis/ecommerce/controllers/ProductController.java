package com.apis.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.CategoryRequest;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.exceptions.CategoryDuplicateException;
import com.apis.ecommerce.exceptions.CategoryHasProductsException;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.services.CategoriesService;
import com.apis.ecommerce.services.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productsService.getProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productsService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id_category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long id) {
        List<Product> products = productsService.getProductByCategory(id);
        return ResponseEntity.ok(products);

    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException {
        Product result = productsService.createProduct(productRequest);
        return ResponseEntity.ok(result);
        /*Revisar este caso */
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNonexistentException {
        productsService.deleteProduct(id);
        return ResponseEntity.ok("Producto eliminado con exito");
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id) throws ProductNonexistentException {
        productsService.updateProduct(id);
        /*Revisar este caso */
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    
}

