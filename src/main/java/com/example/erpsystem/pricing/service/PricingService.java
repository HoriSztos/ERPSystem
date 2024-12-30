package com.example.erpsystem.pricing.service;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingService {
    private final PricingRepository pricingRepository;

    @Autowired
    public PricingService(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    public Pricing saveComponents(Pricing pricing) {
        return pricingRepository.save(pricing);
    }

    public Pricing findById(Long id) {
        return pricingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wyceny o ID: " + id));
    }
public void deleteById(Long id) {
        pricingRepository.deleteById(id);
}


    public BigDecimal calculateManHourValue(Long manHours, BigDecimal totalCost) {
        if (manHours == 0) {
            return BigDecimal.ZERO;
        }
        return totalCost.divide(BigDecimal.valueOf(manHours), 2, BigDecimal.ROUND_HALF_UP);
    }

    public List<Pricing> findAll() {
        return pricingRepository.findAll();
    }
}
