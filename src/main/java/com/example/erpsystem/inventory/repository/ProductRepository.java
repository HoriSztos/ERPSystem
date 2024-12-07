package com.example.erpsystem.inventory.repository;

import com.example.erpsystem.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByStockAsc();
    List<Product> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);
}
