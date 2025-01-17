package com.example.erpsystem.inventory;

import com.example.erpsystem.inventory.model.Product;
import com.example.erpsystem.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindAllByOrderByStockAsc() {
        // Przygotowanie danych testowych
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setStock(5);

        Product product2 = new Product();
        product2.setName("Product B");
        product2.setStock(2);

        productRepository.save(product1);
        productRepository.save(product2);

        // Wywołanie metody
        List<Product> products = productRepository.findAllByOrderByStockAsc();

        // Weryfikacje
        assertEquals(2, products.size());
        assertEquals("Product B", products.get(0).getName());
        assertEquals("Product A", products.get(1).getName());
    }

    @Test
    void testFindByExpirationDateBetween() {
        // Przygotowanie danych testowych
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setExpirationDate(LocalDate.of(2025, 1, 15));

        Product product2 = new Product();
        product2.setName("Product B");
        product2.setExpirationDate(LocalDate.of(2025, 2, 10));

        productRepository.save(product1);
        productRepository.save(product2);

        // Wywołanie metody
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 31);
        List<Product> products = productRepository.findByExpirationDateBetween(startDate, endDate);

        // Weryfikacje
        assertEquals(1, products.size());
        assertEquals("Product A", products.get(0).getName());
    }

    @Test
    void testFindByStockLessThanMinimalStock() {
        // Przygotowanie danych testowych
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setStock(3);
        product1.setMinimalStock(5);

        Product product2 = new Product();
        product2.setName("Product B");
        product2.setStock(10);
        product2.setMinimalStock(5);

        productRepository.save(product1);
        productRepository.save(product2);

        // Wywołanie metody
        List<Product> products = productRepository.findByStockLessThanMinimalStock();

        // Weryfikacje
        assertEquals(1, products.size());
        assertEquals("Product A", products.get(0).getName());
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        // Przygotowanie danych testowych
        Product product1 = new Product();
        product1.setName("Product ABC");

        Product product2 = new Product();
        product2.setName("Another Product");

        productRepository.save(product1);
        productRepository.save(product2);

        // Wywołanie metody
        List<Product> products = productRepository.findByNameContainingIgnoreCase("abc");

        // Weryfikacje
        assertEquals(1, products.size());
        assertEquals("Product ABC", products.get(0).getName());
    }

    @Test
    void testFindAllByOrderByExpirationDateAsc() {
        // Przygotowanie danych testowych
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setExpirationDate(LocalDate.of(2025, 2, 10));

        Product product2 = new Product();
        product2.setName("Product B");
        product2.setExpirationDate(LocalDate.of(2025, 1, 15));

        productRepository.save(product1);
        productRepository.save(product2);

        // Wywołanie metody
        List<Product> products = productRepository.findAllByOrderByExpirationDateAsc();

        // Weryfikacje
        assertEquals(2, products.size());
        assertEquals("Product B", products.get(0).getName());
        assertEquals("Product A", products.get(1).getName());
    }
}
