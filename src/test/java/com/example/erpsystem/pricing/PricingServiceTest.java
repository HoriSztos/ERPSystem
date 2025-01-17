package com.example.erpsystem.pricing;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.repository.PricingRepository;
import com.example.erpsystem.pricing.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PricingServiceTest {

    @Mock
    private PricingRepository pricingRepository;

    @InjectMocks
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveComponents() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L);

        when(pricingRepository.save(pricing)).thenReturn(pricing);

        // Wywołanie metody
        Pricing savedPricing = pricingService.saveComponents(pricing);

        // Weryfikacja
        verify(pricingRepository).save(pricing);
        assertEquals(100L, savedPricing.getManHours());
    }

    @Test
    void testFindByIdSuccess() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(50L);
        when(pricingRepository.findById(1L)).thenReturn(Optional.of(pricing));

        // Wywołanie metody
        Pricing foundPricing = pricingService.findById(1L);

        // Weryfikacja
        verify(pricingRepository).findById(1L);
        assertEquals(50L, foundPricing.getManHours());
    }

    @Test
    void testFindByIdNotFound() {
        // Przygotowanie danych
        when(pricingRepository.findById(1L)).thenReturn(Optional.empty());

        // Wywołanie metody i oczekiwanie wyjątku
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pricingService.findById(1L);
        });

        // Weryfikacja
        assertEquals("Nie znaleziono wyceny o ID: 1", exception.getMessage());
        verify(pricingRepository).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Wywołanie metody
        pricingService.deleteById(1L);

        // Weryfikacja
        verify(pricingRepository).deleteById(1L);
    }

    @Test
    void testCalculateManHourValue() {
        // Przygotowanie danych
        Long manHours = 100L;
        BigDecimal totalCost = BigDecimal.valueOf(5000);

        // Wywołanie metody
        BigDecimal result = pricingService.calculateManHourValue(manHours, totalCost);

        // Weryfikacja
        assertEquals(BigDecimal.valueOf(50.00).setScale(2), result);
    }

    @Test
    void testCalculateManHourValueWithZeroManHours() {
        // Przygotowanie danych
        Long manHours = 0L;
        BigDecimal totalCost = BigDecimal.valueOf(5000);

        // Wywołanie metody
        BigDecimal result = pricingService.calculateManHourValue(manHours, totalCost);

        // Weryfikacja
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testFindAll() {
        // Przygotowanie danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L);
        when(pricingRepository.findAll()).thenReturn(Collections.singletonList(pricing));

        // Wywołanie metody
        List<Pricing> allPricings = pricingService.findAll();

        // Weryfikacja
        verify(pricingRepository).findAll();
        assertEquals(1, allPricings.size());
        assertEquals(100L, allPricings.get(0).getManHours());
    }
}
