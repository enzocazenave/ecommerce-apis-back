package com.apis.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.ProductImages;
import com.apis.ecommerce.entities.dto.ProductImageUpload;
import com.apis.ecommerce.exceptions.ProductImagesNonexistentException;

@Service
public interface ProductImagesService {
    ProductImages uploadImage(Long productId, ProductImageUpload productImageUpload);
    List<ProductImages> getProductImages(Long productId);
    void deleteProductImages(Long imageId) throws ProductImagesNonexistentException;
}