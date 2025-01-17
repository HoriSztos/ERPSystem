package com.example.erpsystem.pricing;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PricingControllerTest {

    @Mock
    private PricingService pricingService;

    @Mock
    private Model model;

    @InjectMocks
    private PricingController pricingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowPricingFormWithRecords() {
        // Mock danych
        List<Pricing> pricings = Collections.emptyList();

        when(pricingService.findAll()).thenReturn(pricings);

        // Wywołanie metody
        String viewName = pricingController.showPricingFormWithRecords(model);

        // Weryfikacje
        verify(model).addAttribute(eq("components"), any(Pricing.class));
        verify(model).addAttribute("pricings", pricings);
        assertEquals("pricing_form", viewName);
    }

    @Test
    void testShowPricingList() {
        // Mock danych
        List<Pricing> pricingList = Collections.emptyList();

        when(pricingService.findAll()).thenReturn(pricingList);

        // Wywołanie metody
        String viewName = pricingController.showPricingList(model);

        // Weryfikacje
        verify(pricingService).findAll();
        verify(model).addAttribute("pricingList", pricingList);
        assertEquals("pricing_list", viewName);
    }

    @Test
    void testLoadPricing() {
        // Mock danych
        Long id = 1L;
        Pricing pricing = new Pricing();

        when(pricingService.findById(id)).thenReturn(pricing);

        // Wywołanie metody
        String viewName = pricingController.loadPricing(id, model);

        // Weryfikacje
        verify(pricingService).findById(id);
        verify(model).addAttribute("components", pricing);
        assertEquals("pricing_form", viewName);
    }

    @Test
    void testDeleteAppointment() {
        // Mock danych
        Long id = 1L;

        // Wywołanie metody
        String viewName = pricingController.deleteAppointment(id);

        // Weryfikacje
        verify(pricingService).deleteById(id);
        assertEquals("redirect:/pricing/list", viewName);
    }

    @Test
    void testCalculatePricing() {
        // Mock danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L); // Ustawienie Long dla liczby godzin
        pricing.setBusinessOperating(BigDecimal.valueOf(1000));
        pricing.setEmployees(BigDecimal.valueOf(2000));
        pricing.setEquipment(BigDecimal.valueOf(1500));
        pricing.setProducts(BigDecimal.valueOf(500));
        pricing.setTraining(BigDecimal.valueOf(300));
        pricing.setIncome(BigDecimal.valueOf(4000));
        pricing.setTaxes(BigDecimal.valueOf(1200));

        Long manHours = pricing.getManHours(); // Pobranie Long
        BigDecimal totalCost = pricing.getBusinessOperating()
                .add(pricing.getEmployees())
                .add(pricing.getEquipment())
                .add(pricing.getProducts())
                .add(pricing.getTraining())
                .add(pricing.getIncome())
                .add(pricing.getTaxes());

        BigDecimal expectedManHourValue = BigDecimal.valueOf(150);

        // Mockowanie metody
        when(pricingService.calculateManHourValue(eq(manHours), eq(totalCost)))
                .thenReturn(expectedManHourValue);

        // Wywołanie metody kontrolera
        String viewName = pricingController.calculatePricing(pricing, model);

        // Weryfikacje
        verify(pricingService).calculateManHourValue(eq(manHours), eq(totalCost));
        verify(model).addAttribute("components", pricing);
        verify(model).addAttribute("manHourValue", expectedManHourValue);
        assertEquals("pricing_result", viewName);
    }

    @Test
    void testSaveComponents() {
        // Mock danych
        Pricing pricing = new Pricing();
        pricing.setManHours(100L); // Ustawienie Long dla liczby godzin
        pricing.setBusinessOperating(BigDecimal.valueOf(1000));
        pricing.setEmployees(BigDecimal.valueOf(2000));
        pricing.setEquipment(BigDecimal.valueOf(1500));
        pricing.setProducts(BigDecimal.valueOf(500));
        pricing.setTraining(BigDecimal.valueOf(300));
        pricing.setIncome(BigDecimal.valueOf(4000));
        pricing.setTaxes(BigDecimal.valueOf(1200));

        Long manHours = pricing.getManHours(); // Pobranie Long
        BigDecimal totalCost = pricing.getBusinessOperating()
                .add(pricing.getEmployees())
                .add(pricing.getEquipment())
                .add(pricing.getProducts())
                .add(pricing.getTraining())
                .add(pricing.getIncome())
                .add(pricing.getTaxes());

        BigDecimal expectedManHourValue = BigDecimal.valueOf(150);

        // Mockowanie metody
        when(pricingService.calculateManHourValue(eq(manHours), eq(totalCost)))
                .thenReturn(expectedManHourValue);

        // Wywołanie metody kontrolera
        String viewName = pricingController.saveComponents(pricing, model);

        // Weryfikacje
        verify(pricingService).saveComponents(pricing);
        verify(model).addAttribute("successMessage", "Dane zostały zapisane pomyślnie!");
        verify(model).addAttribute("components", pricing);
        verify(model).addAttribute("manHourValue", expectedManHourValue);
        assertEquals("pricing_result", viewName);
    }

}
