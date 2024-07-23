package com.apis.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.services.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size
    ) {
        if (page == null || size == null)
            return ResponseEntity.ok(productsService.getProduct(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productsService.getProduct(PageRequest.of(page, size)));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productsService.getProductsByCategoryId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productsService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException {
        Product result = productsService.createProduct(productRequest);
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNonexistentException {
        productsService.deleteProduct(id);
        return ResponseEntity.ok("Producto eliminado con exito");
    }
    
    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest) throws ProductNonexistentException {
        productsService.updateProduct(productUpdateRequest);
        return ResponseEntity.ok("Producto editado con exito");
    }
}

