package com.example.erpsystem.pricing;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.repository.PricingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PricingRepositoryTest {

    @Autowired
    private PricingRepository pricingRepository;

    @Test
    void testSavePricing() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L);
        pricing.setBusinessOperating(BigDecimal.valueOf(2000));
        pricing.setEmployees(BigDecimal.valueOf(3000));
        pricing.setEquipment(BigDecimal.valueOf(1500));
        pricing.setProducts(BigDecimal.valueOf(500));
        pricing.setTraining(BigDecimal.valueOf(300));
        pricing.setIncome(BigDecimal.valueOf(4000));
        pricing.setTaxes(BigDecimal.valueOf(1200));

        // Zapisanie encji
        Pricing savedPricing = pricingRepository.save(pricing);

        // Weryfikacja
        assertNotNull(savedPricing.getId());
        assertEquals(100L, savedPricing.getManHours());
        assertEquals(BigDecimal.valueOf(2000), savedPricing.getBusinessOperating());
    }

    @Test
    void testFindById() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(50L);
        pricing.setBusinessOperating(BigDecimal.valueOf(1500));

        // Zapisanie encji
        Pricing savedPricing = pricingRepository.save(pricing);

        // Pobranie encji po ID
        Optional<Pricing> foundPricing = pricingRepository.findById(savedPricing.getId());

        // Weryfikacja
        assertTrue(foundPricing.isPresent());
        assertEquals(50L, foundPricing.get().getManHours());
    }

    @Test
    void testFindAll() {
        // Przygotowanie danych
        Pricing pricing1 = new Pricing();
        pricing1.setManHours(30L);
        pricing1.setBusinessOperating(BigDecimal.valueOf(1000));

        Pricing pricing2 = new Pricing();
        pricing2.setManHours(60L);
        pricing2.setBusinessOperating(BigDecimal.valueOf(2000));

        // Zapisanie encji
        pricingRepository.save(pricing1);
        pricingRepository.save(pricing2);

        // Pobranie wszystkich encji
        var allPricings = pricingRepository.findAll();

        // Weryfikacja
        assertEquals(2, allPricings.size());
    }

    @Test
    void testDeletePricing() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L);
        pricing.setBusinessOperating(BigDecimal.valueOf(2000));

        // Zapisanie encji
        Pricing savedPricing = pricingRepository.save(pricing);

        // Usunięcie encji
        pricingRepository.deleteById(savedPricing.getId());

        // Weryfikacja
        Optional<Pricing> foundPricing = pricingRepository.findById(savedPricing.getId());
        assertFalse(foundPricing.isPresent());
    }
}
