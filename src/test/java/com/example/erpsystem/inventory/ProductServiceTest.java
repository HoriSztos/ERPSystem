package com.example.erpsystem.inventory;

import com.example.erpsystem.inventory.model.Product;
import com.example.erpsystem.inventory.repository.ProductRepository;
import com.example.erpsystem.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Mock danych
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.getAllProducts();

        // Weryfikacje
        verify(productRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetProductById() {
        // Mock danych
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Wywołanie metody
        Optional<Product> result = productService.getProductById(productId);

        // Weryfikacje
        verify(productRepository).findById(productId);
        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getId());
    }

    @Test
    void testSaveProduct() {
        // Mock danych
        Product product = new Product();
        product.setName("Product A");

        // Wywołanie metody
        productService.saveProduct(product);

        // Weryfikacje
        verify(productRepository).save(product);
    }

    @Test
    void testDeleteProduct() {
        // Mock danych
        Long productId = 1L;

        // Wywołanie metody
        productService.deleteProduct(productId);

        // Weryfikacje
        verify(productRepository).deleteById(productId);
    }

    @Test
    void testGetProductsExpiringSoon() {
        // Mock danych
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(7);

        Product product1 = new Product();
        product1.setExpirationDate(today.plusDays(3));

        Product product2 = new Product();
        product2.setExpirationDate(today.plusDays(5));

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findByExpirationDateBetween(today, threshold)).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.getProductsExpiringSoon(7);

        // Weryfikacje
        verify(productRepository).findByExpirationDateBetween(today, threshold);
        assertEquals(2, result.size());
    }

    @Test
    void testGetProductsBelowMinimumStock() {
        // Mock danych
        Product product1 = new Product();
        product1.setStock(3);
        product1.setMinimalStock(5);

        Product product2 = new Product();
        product2.setStock(2);
        product2.setMinimalStock(4);

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findByStockLessThanMinimalStock()).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.getProductsBelowMinimumStock();

        // Weryfikacje
        verify(productRepository).findByStockLessThanMinimalStock();
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllProductsSortedByStock() {
        // Mock danych
        Product product1 = new Product();
        product1.setStock(3);

        Product product2 = new Product();
        product2.setStock(10);

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAllByOrderByStockAsc()).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.getAllProductsSortedByStock();

        // Weryfikacje
        verify(productRepository).findAllByOrderByStockAsc();
        assertEquals(2, result.size());
    }

    @Test
    void testSearchProductsByName() {
        // Mock danych
        Product product1 = new Product();
        product1.setName("Product A");

        Product product2 = new Product();
        product2.setName("Another Product");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findByNameContainingIgnoreCase("Product")).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.searchProductsByName("Product");

        // Weryfikacje
        verify(productRepository).findByNameContainingIgnoreCase("Product");
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllProductsSortedByDate() {
        // Mock danych
        Product product1 = new Product();
        product1.setExpirationDate(LocalDate.of(2025, 1, 10));

        Product product2 = new Product();
        product2.setExpirationDate(LocalDate.of(2025, 1, 5));

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAllByOrderByExpirationDateAsc()).thenReturn(products);

        // Wywołanie metody
        List<Product> result = productService.getAllProductsSortedByDate();

        // Weryfikacje
        verify(productRepository).findAllByOrderByExpirationDateAsc();
        assertEquals(2, result.size());
    }
}
