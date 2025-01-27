package com.apis.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import com.apis.ecommerce.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductResponse;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.services.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productsService.getProduct());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productsService.getProductsByCategoryId(id));
    }

    @GetMapping("/productsByPrice")
    public ResponseEntity<List<ProductResponse>> getProductsByPrice(@RequestParam(required = true) Double priceMin,@RequestParam(required = true) Double priceMax) {
        return ResponseEntity.ok(productsService.getProductsByPrice(priceMin,priceMax));
    }
    @GetMapping("/search/{nameProduct}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String nameProduct) {
        return ResponseEntity.ok(productsService.getProductByName(nameProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<ProductResponse> product = productsService.getProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/matching_sizes")
    public ResponseEntity<List<ProductResponse>> getProductByIdAndSize(@PathVariable Long id) {
        List<ProductResponse> products = productsService.getProductByIdAndSize(id);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{name}/matching_sizes_by_name")
    public ResponseEntity<List<Product>> getProductByNameAndSize(@PathVariable String name) {
        List<Product> products = productsService.getProductByNameAndSize(name);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException {
        if (productRequest.getPrice() <= 0) {
            throw new InvalidPriceException();
        }

        if (productRequest.getStock() <= 0) {
            throw new InvalidStockException();
        }

        Product result = productsService.createProduct(productRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNonexistentException {
        productsService.deleteProduct(id);
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest productUpdateRequest) throws ProductNonexistentException, CategoryNonexistentException {
        if (productUpdateRequest.getPrice() != null && productUpdateRequest.getPrice() <= 0) {
            throw new InvalidPriceException();
        }

        if (productUpdateRequest.getStock() != null && productUpdateRequest.getStock() < 0) {
            throw new InvalidStockException();
        }

        productsService.updateProduct(id, productUpdateRequest);
        return ResponseEntity.ok("Producto editado con exito");
    }
}

