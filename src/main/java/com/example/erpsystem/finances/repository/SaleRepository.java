package com.example.erpsystem.finances.repository;

import com.example.erpsystem.finances.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
