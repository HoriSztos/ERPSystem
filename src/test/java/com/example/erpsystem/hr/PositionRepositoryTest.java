package com.example.erpsystem.hr;

import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PositionRepositoryTest {

    @Autowired
    private PositionRepository positionRepository;

    @Test
    void testSavePosition() {
        // Przygotowanie danych
        Position position = new Position();
        position.setName("Manager");

        // Zapisanie pozycji
        Position savedPosition = positionRepository.save(position);

        // Weryfikacja
        assertNotNull(savedPosition.getId());
        assertEquals("Manager", savedPosition.getName());
    }

    @Test
    void testFindPositionById() {
        // Przygotowanie danych
        Position position = new Position();
        position.setName("Developer");

        // Zapisanie pozycji
        Position savedPosition = positionRepository.save(position);

        // Pobranie pozycji po ID
        Optional<Position> foundPosition = positionRepository.findById(savedPosition.getId());

        // Weryfikacja
        assertTrue(foundPosition.isPresent());
        assertEquals("Developer", foundPosition.get().getName());
    }

    @Test
    void testFindAllPositions() {
        // Przygotowanie danych
        Position position1 = new Position();
        position1.setName("Manager");

        Position position2 = new Position();
        position2.setName("Developer");

        // Zapisanie pozycji
        positionRepository.save(position1);
        positionRepository.save(position2);

        // Pobranie wszystkich pozycji
        long count = positionRepository.count();

        // Weryfikacja
        assertEquals(2, count);
    }

    @Test
    void testDeletePosition() {
        // Przygotowanie danych
        Position position = new Position();
        position.setName("Tester");

        // Zapisanie pozycji
        Position savedPosition = positionRepository.save(position);

        // Usunięcie pozycji
        positionRepository.deleteById(savedPosition.getId());

        // Weryfikacja
        Optional<Position> foundPosition = positionRepository.findById(savedPosition.getId());
        assertFalse(foundPosition.isPresent());
    }
}

