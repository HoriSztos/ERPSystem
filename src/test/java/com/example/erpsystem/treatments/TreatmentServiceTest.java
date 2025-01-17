package com.example.erpsystem.treatments;

import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.repository.TreatmentRepository;
import com.example.erpsystem.treatments.service.TreatmentService;
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

class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private TreatmentService treatmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllServices() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Facial");
        when(treatmentRepository.findAll()).thenReturn(Collections.singletonList(treatment));

        // Wywołanie metody
        List<Treatment> treatments = treatmentService.getAllServices();

        // Weryfikacje
        assertEquals(1, treatments.size());
        assertEquals("Facial", treatments.get(0).getName());
        verify(treatmentRepository).findAll();
    }

    @Test
    void testGetServiceByIDSuccess() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Massage");
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(treatment));

        // Wywołanie metody
        Treatment foundTreatment = treatmentService.getServiceByID(1L);

        // Weryfikacje
        assertEquals("Massage", foundTreatment.getName());
        verify(treatmentRepository).findById(1L);
    }

    @Test
    void testGetServiceByIDNotFound() {
        // Przygotowanie danych
        when(treatmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Wywołanie metody i oczekiwanie wyjątku
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            treatmentService.getServiceByID(1L);
        });

        // Weryfikacje
        assertEquals("Usługi nie znaleziono", exception.getMessage());
        verify(treatmentRepository).findById(1L);
    }

    @Test
    void testSaveService() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Pedicure");
        when(treatmentRepository.save(treatment)).thenReturn(treatment);

        // Wywołanie metody
        Treatment savedTreatment = treatmentService.saveService(treatment);

        // Weryfikacje
        assertEquals("Pedicure", savedTreatment.getName());
        verify(treatmentRepository).save(treatment);
    }

    @Test
    void testDeleteService() {
        // Wywołanie metody
        treatmentService.deleteService(1L);

        // Weryfikacje
        verify(treatmentRepository).deleteById(1L);
    }

    @Test
    void testFindAll() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Manicure");
        when(treatmentRepository.findAll()).thenReturn(Collections.singletonList(treatment));

        // Wywołanie metody
        List<Treatment> treatments = treatmentService.findAll();

        // Weryfikacje
        assertEquals(1, treatments.size());
        assertEquals("Manicure", treatments.get(0).getName());
        verify(treatmentRepository).findAll();
    }
}
