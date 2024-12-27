package com.example.erpsystem.pricing.repository;

import com.example.erpsystem.pricing.model.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<Pricing, Long> {
}
