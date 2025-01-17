package com.example.erpsystem.hr;

import com.example.erpsystem.hr.controller.PositionsController;
import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.service.EmployeeService;
import com.example.erpsystem.hr.service.PositionService;
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
import static org.mockito.Mockito.*;

class PositionsControllerTest {

    @Mock
    private PositionService positionService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Model model;

    @InjectMocks
    private PositionsController positionsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListPositions() {
        // Mock danych
        List<Position> positions = Collections.emptyList();

        when(positionService.getAllPositions()).thenReturn(positions);

        // Wywołanie metody
        String viewName = positionsController.listPositions(model);

        // Weryfikacje
        verify(positionService).getAllPositions();
        verify(model).addAttribute("positions", positions);
        assertEquals("positions", viewName);
    }

    @Test
    void testAddPositionForm() {
        // Wywołanie metody
        String viewName = positionsController.addPositionForm(model);

        // Weryfikacje
        verify(model).addAttribute(eq("position"), any(Position.class));
        assertEquals("positions_add", viewName);
    }

    @Test
    void testSavePosition() {
        // Mock danych
        Position position = new Position();

        // Wywołanie metody
        String viewName = positionsController.savePosition(position);

        // Weryfikacje
        verify(positionService).savePosition(position);
        assertEquals("redirect:/hr/positions", viewName);
    }

    @Test
    void testEditPositionForm() {
        // Mock danych
        Long positionId = 1L;
        Position position = new Position();

        when(positionService.getPositionById(positionId)).thenReturn(position);

        // Wywołanie metody
        String viewName = positionsController.editPositionForm(positionId, model);

        // Weryfikacje
        verify(positionService).getPositionById(positionId);
        verify(model).addAttribute("position", position);
        assertEquals("positions_edit", viewName);
    }

    @Test
    void testUpdatePosition() {
        // Mock danych
        Position position = new Position();

        // Wywołanie metody
        String viewName = positionsController.updatePosition(position);

        // Weryfikacje
        verify(positionService).savePosition(position);
        assertEquals("redirect:/hr/positions", viewName);
    }

    @Test
    void testDeletePosition() {
        // Mock danych
        Long positionId = 1L;

        // Wywołanie metody
        String viewName = positionsController.deletePosition(positionId);

        // Weryfikacje
        verify(employeeService).updateEmployeesPosition(positionId);
        verify(positionService).deletePosition(positionId);
        assertEquals("redirect:/hr/positions", viewName);
    }
}
