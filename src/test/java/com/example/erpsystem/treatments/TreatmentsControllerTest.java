package com.example.erpsystem.treatments;

import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.service.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TreatmentsControllerTest {

    @Mock
    private TreatmentService treatmentService;

    @Mock
    private Model model;

    @InjectMocks
    private TreatmentsController treatmentsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListTreatments() {
        // Mock danych
        List<Treatment> treatments = Collections.emptyList();
        when(treatmentService.getAllServices()).thenReturn(treatments);

        // Wywołanie metody
        String viewName = treatmentsController.listTreatments(model);

        // Weryfikacje
        verify(treatmentService).getAllServices();
        verify(model).addAttribute("treatments", treatments);
        assertEquals("treatments", viewName);
    }

    @Test
    void testAddTreatmentForm() {
        // Wywołanie metody
        String viewName = treatmentsController.addTreatmentForm(model);

        // Weryfikacje
        verify(model).addAttribute(eq("treatment"), any(Treatment.class));
        assertEquals("treatments_add", viewName);
    }

    @Test
    void testSaveTreatment() {
        // Mock danych
        Treatment treatment = new Treatment();

        // Wywołanie metody
        String viewName = treatmentsController.saveTreatment(treatment);

        // Weryfikacje
        verify(treatmentService).saveService(treatment);
        assertEquals("redirect:/treatments", viewName);
    }

    @Test
    void testEditTreatmentForm() {
        // Mock danych
        Long treatmentId = 1L;
        Treatment treatment = new Treatment();
        when(treatmentService.getServiceByID(treatmentId)).thenReturn(treatment);

        // Wywołanie metody
        String viewName = treatmentsController.editTreatmentForm(treatmentId, model);

        // Weryfikacje
        verify(treatmentService).getServiceByID(treatmentId);
        verify(model).addAttribute("treatment", treatment);
        assertEquals("treatments_edit", viewName);
    }

    @Test
    void testUpdateTreatment() {
        // Mock danych
        Treatment treatment = new Treatment();

        // Wywołanie metody
        String viewName = treatmentsController.updateTreatment(treatment);

        // Weryfikacje
        verify(treatmentService).saveService(treatment);
        assertEquals("redirect:/treatments", viewName);
    }

    @Test
    void testDeleteTreatment() {
        // Mock danych
        Long treatmentId = 1L;

        // Wywołanie metody
        String viewName = treatmentsController.deleteTreatment(treatmentId);

        // Weryfikacje
        verify(treatmentService).deleteService(treatmentId);
        assertEquals("redirect:/treatments", viewName);
    }
}

