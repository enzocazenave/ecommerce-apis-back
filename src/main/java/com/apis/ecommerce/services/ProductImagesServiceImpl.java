package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.ProductImages;
import com.apis.ecommerce.entities.dto.ProductImageUpload;
import com.apis.ecommerce.exceptions.ProductImagesNonexistentException;
import com.apis.ecommerce.repositories.ProductImagesRepository;
import com.apis.ecommerce.repositories.ProductRepository;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductImages uploadImage(Long productId, ProductImageUpload productImageUpload) {
        ProductImages productImages = new ProductImages();
        Optional<Product> product = productRepository.findById(productId);
        productImages.setProduct(product.get());
        productImages.setUrlImage(productImageUpload.getUrlImage());
        return productImagesRepository.save(productImages);
    }

    public List<ProductImages> getProductImages(Long productId) {
        return productImagesRepository.findByProductId(productId);
    }

    public void deleteProductImages(Long imageId) throws ProductImagesNonexistentException {
        Optional<ProductImages> productImages = productImagesRepository.findById(imageId);

        if (productImages.isEmpty()) {
            throw new ProductImagesNonexistentException();
        }

        productImagesRepository.deleteById(imageId);
    }
}
