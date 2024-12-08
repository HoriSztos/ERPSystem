package com.example.erpsystem.inventory.repository;

import com.example.erpsystem.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByStockAsc();
    List<Product> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);

    // Produkty poniżej minimalnego stanu magazynowego
    @Query("SELECT p FROM Product p WHERE p.stock < p.minimumStock")
    List<Product> findByStockLessThanMinimumStock();

    // Produkty wyszukiwane po nazwie
    List<Product> findByNameContainingIgnoreCase(String name);

}
