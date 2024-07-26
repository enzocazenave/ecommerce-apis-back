package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.apis.ecommerce.exceptions.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.Category;
import com.apis.ecommerce.entities.Product;
import com.apis.ecommerce.entities.dto.ProductRequest;
import com.apis.ecommerce.entities.dto.ProductUpdateRequest;
import com.apis.ecommerce.exceptions.CategoryNonexistentException;
import com.apis.ecommerce.exceptions.ProductDuplicateException;
import com.apis.ecommerce.exceptions.ProductNonexistentException;
import com.apis.ecommerce.repositories.CategoriesRepository;
import com.apis.ecommerce.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    public Page<Product> getProduct(PageRequest pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductByIdAndSize(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            return List.of();
        }

        Product mainProduct = product.get();
        List<Product> products = productRepository.findByExactName(mainProduct.getName());

        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public Product createProduct(ProductRequest p) throws ProductDuplicateException {
        Optional<Category> category = categoriesRepository.findById(p.getIdCategory());
        Product product = new Product(p.getName(), p.getStock(), p.getPrice(), p.getDescription(), p.getSize());
        product.setCategory(category.get());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) throws ProductNonexistentException {
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty()) {
            throw new ProductNonexistentException();
        }
        productRepository.deleteById(id);
        //productRepository.removedLogical(id); //borrado logico
    }

    public void updateProduct(Long id, ProductUpdateRequest productRequest) throws ProductNonexistentException, CategoryNonexistentException {
        Optional<Product> p = productRepository.findById(id);

        if (p.isEmpty()) {
            throw new ProductNonexistentException();
        }

        Product product = p.get();

        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }
        if (productRequest.getStock() != null) {
            product.setStock(productRequest.getStock());
        }
        if (productRequest.getPrice() != null) {
            product.setPrice(productRequest.getPrice());
        }
        if (productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }
        if (productRequest.getSize() != null) {
            product.setSize(productRequest.getSize());
        }

        if (productRequest.getIdCategory() != null) {
            Optional<Category> category = categoriesRepository.findById(productRequest.getIdCategory());
            if (category.isEmpty()) {
                throw new CategoryNonexistentException();
            }

            product.setCategory(category.get());
        }

        productRepository.save(product);
    }

    public void deductStock(Long id, int quantity) throws ProductNonexistentException, InsufficientStockException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNonexistentException();
        }

        Product product = optionalProduct.get();
        int currentStock = product.getStock();
        int newStock = currentStock - quantity;
        if (newStock < 0) {
            throw new InsufficientStockException();
        }
        product.setStock(newStock);

        productRepository.save(product);
    }

    public void reduceStockBy(Long id, int quantity) throws ProductNonexistentException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNonexistentException();
        }

        Product product = optionalProduct.get();
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    public List<Product> getProductByName(String nombre) {
        return productRepository.findByName(nombre);
    }

    public List<Product> getProductsByPrice(Double priceMin,Double priceMax ) {
        return productRepository.findByPrice(priceMin,priceMax);
    }

}
