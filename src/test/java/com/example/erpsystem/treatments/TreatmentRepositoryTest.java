package com.example.erpsystem.treatments;

import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TreatmentRepositoryTest {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Test
    void testSaveTreatment() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Facial");
        treatment.setDescription("Relaxing facial treatment");
        treatment.setPrice(BigDecimal.valueOf(100.00));

        // Zapisanie encji
        Treatment savedTreatment = treatmentRepository.save(treatment);

        // Weryfikacja
        assertNotNull(savedTreatment.getId());
        assertEquals("Facial", savedTreatment.getName());
        assertEquals("Relaxing facial treatment", savedTreatment.getDescription());
        assertEquals(BigDecimal.valueOf(100.00), savedTreatment.getPrice());
    }

    @Test
    void testFindById() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Massage");
        treatment.setDescription("Full body massage");
        treatment.setPrice(BigDecimal.valueOf(150.00));

        // Zapisanie encji
        Treatment savedTreatment = treatmentRepository.save(treatment);

        // Pobranie encji po ID
        Optional<Treatment> foundTreatment = treatmentRepository.findById(savedTreatment.getId());

        // Weryfikacja
        assertTrue(foundTreatment.isPresent());
        assertEquals("Massage", foundTreatment.get().getName());
    }

    @Test
    void testFindAll() {
        // Przygotowanie danych
        Treatment treatment1 = new Treatment();
        treatment1.setName("Manicure");
        treatment1.setDescription("Nail care treatment");
        treatment1.setPrice(BigDecimal.valueOf(50.00));

        Treatment treatment2 = new Treatment();
        treatment2.setName("Pedicure");
        treatment2.setDescription("Foot care treatment");
        treatment2.setPrice(BigDecimal.valueOf(70.00));

        // Zapisanie encji
        treatmentRepository.save(treatment1);
        treatmentRepository.save(treatment2);

        // Pobranie wszystkich encji
        var allTreatments = treatmentRepository.findAll();

        // Weryfikacja
        assertEquals(2, allTreatments.size());
    }

    @Test
    void testDeleteTreatment() {
        // Przygotowanie danych
        Treatment treatment = new Treatment();
        treatment.setName("Haircut");
        treatment.setDescription("Professional haircut");
        treatment.setPrice(BigDecimal.valueOf(80.00));

        // Zapisanie encji
        Treatment savedTreatment = treatmentRepository.save(treatment);

        // Usunięcie encji
        treatmentRepository.deleteById(savedTreatment.getId());

        // Weryfikacja
        Optional<Treatment> foundTreatment = treatmentRepository.findById(savedTreatment.getId());
        assertFalse(foundTreatment.isPresent());
    }
}
