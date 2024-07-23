package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.entities.Product;

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

    public Product createProduct(Product p) throws ProductDuplicateException {
        if (!productRepository.findByName(p.getName()).isEmpty()) {
            throw new ProductDuplicateException();
        }
        Product product = new Product(p);
        return productRepository.save(product);
    }

    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category.getId()); 
    }

    public void deleteProduct(Product p) throws ProductNonexistentException {
        if(!productRepository.existsById(p.getId())) {
            throw new ProductNonexistentException();     
         }
        productRepository.deleteById(p.getId()); 
        //productRepository.removedLogical(p.getId()); //borrado logico
        productRepository.save(p); //esto es asi?
    }

    public void updateProduct(Product p) throws ProductNonexistentException {
        if(!productRepository.existsById(p.getId())) {
           throw new ProductNonexistentException();     
        }
        productRepository.save(p) ;
    }

}
