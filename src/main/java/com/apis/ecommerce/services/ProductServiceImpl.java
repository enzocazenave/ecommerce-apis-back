package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getProduct() {
        return productRepository.findAll(); 
        //return productRepository.findAllAvailable(); //si hacemos un borrado logico
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductRequest p) throws ProductDuplicateException {
        if (!productRepository.findByName(p.getName()).isEmpty()) {
            throw new ProductDuplicateException();
        }
        Product product = new Product(p.getName(),p.getStock(),p.getPrice(),p.getDescription(),p.getSize());
        return productRepository.save(product);
    }

    public List<Product> getProductByCategory(Long idCategory) {
        return productRepository.findByCategory(idCategory); 
    }

    public void deleteProduct(Long id) throws ProductNonexistentException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()) {
            throw new ProductNonexistentException();     
         }
        productRepository.deleteById(id); 
        //productRepository.removedLogical(id); //borrado logico
    }

    public void updateProduct(ProductUpdateRequest productRequest) throws ProductNonexistentException {
        Optional<Product> p = productRepository.findById(productRequest.getId());
        if(p.isEmpty()) {
           throw new ProductNonexistentException();     
        }
        Product product = p.get();
        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setSize(productRequest.getSize());
        
        productRepository.save(product);
    }

}
