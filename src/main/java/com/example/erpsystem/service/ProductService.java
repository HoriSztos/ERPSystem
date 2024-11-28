package com.example.erpsystem.service;

import com.example.erpsystem.model.Product;
import com.example.erpsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){this.productRepository = productRepository;}
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produkt nie znaleziony"));
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
