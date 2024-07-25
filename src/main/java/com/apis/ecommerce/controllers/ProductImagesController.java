package com.apis.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.ProductImages;
import com.apis.ecommerce.entities.dto.ProductImageUpload;
import com.apis.ecommerce.exceptions.ProductImagesNonexistentException;
import com.apis.ecommerce.services.ProductImagesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/products/images")
public class ProductImagesController {
    @Autowired
    private ProductImagesService productImagesService;

    @PostMapping("/{productId}")
    public ResponseEntity<ProductImages> uploadImage(@PathVariable Long productId, @RequestBody ProductImageUpload productImageUpload) {
        ProductImages image = productImagesService.uploadImage(productId, productImageUpload);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductImages>> getProductImages(@PathVariable Long productId) {
        List<ProductImages> images = productImagesService.getProductImages(productId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage( @PathVariable Long imageId) throws ProductImagesNonexistentException {
        productImagesService.deleteProductImages(imageId);
        return ResponseEntity.ok("Imagen eliminada con exito");
    }
}
