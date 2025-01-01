package com.example.erpsystem.inventory.service;


import com.example.erpsystem.inventory.model.Product;
import com.example.erpsystem.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {productRepository.save(product);}

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsExpiringSoon(int days) {
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(days);
        return productRepository.findByExpirationDateBetween(today, threshold);
    }

    public List<Product> getProductsBelowMinimumStock() {
        return productRepository.findByStockLessThanMinimalStock();
    }

    public List<Product> getAllProductsSortedByStock() {
        return productRepository.findAllByOrderByStockAsc();
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getAllProductsSortedByDate() {return productRepository.findAllByOrderByExpirationDateAsc();
    }


}
