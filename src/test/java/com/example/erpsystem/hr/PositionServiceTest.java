package com.example.erpsystem.hr;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.EmployeeRepository;
import com.example.erpsystem.hr.repository.PositionRepository;
import com.example.erpsystem.hr.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PositionService positionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPositions() {
        // Mock danych
        List<Position> positions = Arrays.asList(new Position(), new Position());
        when(positionRepository.findAll()).thenReturn(positions);

        // Wywołanie metody
        List<Position> result = positionService.getAllPositions();

        // Weryfikacje
        verify(positionRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetPositionById() {
        // Mock danych
        Long positionId = 1L;
        Position position = new Position();
        position.setId(positionId);

        when(positionRepository.findById(positionId)).thenReturn(Optional.of(position));

        // Wywołanie metody
        Position result = positionService.getPositionById(positionId);

        // Weryfikacje
        verify(positionRepository).findById(positionId);
        assertEquals(positionId, result.getId());
    }

    @Test
    void testGetPositionById_NotFound() {
        // Mock danych
        Long positionId = 1L;

        when(positionRepository.findById(positionId)).thenReturn(Optional.empty());

        // Wywołanie metody
        Position result = positionService.getPositionById(positionId);

        // Weryfikacje
        verify(positionRepository).findById(positionId);
        assertEquals(null, result);
    }

    @Test
    void testSavePosition() {
        // Mock danych
        Position position = new Position();
        position.setName("Manager");

        when(positionRepository.save(position)).thenReturn(position);

        // Wywołanie metody
        Position result = positionService.savePosition(position);

        // Weryfikacje
        verify(positionRepository).save(position);
        assertEquals("Manager", result.getName());
    }

    @Test
    void testDeletePosition() {
        // Mock danych
        Long positionId = 1L;

        // Wywołanie metody
        positionService.deletePosition(positionId);

        // Weryfikacje
        verify(positionRepository).deleteById(positionId);
    }

    @Test
    void testFindByPositionId() {
        // Mock danych
        Long positionId = 1L;
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        when(employeeRepository.findByPositionId(positionId)).thenReturn(employees);

        // Wywołanie metody
        List<Employee> result = positionService.findByPositionId(positionId);

        // Weryfikacje
        verify(employeeRepository).findByPositionId(positionId);
        assertEquals(2, result.size());
    }
}
