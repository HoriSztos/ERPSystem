package com.example.erpsystem.hr;

import com.example.erpsystem.hr.controller.EmployeeController;
import com.example.erpsystem.hr.model.Employee;
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

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private PositionService positionService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListEmployees() {
        // Mock danych
        List<Employee> employees = Collections.emptyList();

        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Wywołanie metody
        String viewName = employeeController.listEmployees(model);

        // Weryfikacje
        verify(employeeService).getAllEmployees();
        verify(model).addAttribute("employees", employees);
        assertEquals("employee_list", viewName);
    }

    @Test
    void testAddEmployeeForm() {
        // Mock danych
        List<Position> positions = Collections.emptyList();

        when(positionService.getAllPositions()).thenReturn(positions);

        // Wywołanie metody
        String viewName = employeeController.addEmployeeForm(model);

        // Weryfikacje
        verify(positionService).getAllPositions();
        verify(model).addAttribute(eq("employee"), any(Employee.class));
        verify(model).addAttribute("positions", positions);
        assertEquals("employee_add", viewName);
    }

    @Test
    void testSaveEmployee() {
        // Mock danych
        Employee employee = new Employee();

        // Wywołanie metody
        String viewName = employeeController.saveEmployee(employee);

        // Weryfikacje
        verify(employeeService).saveEmployee(employee);
        assertEquals("redirect:/hr/employees", viewName);
    }

    @Test
    void testEditEmployeeForm() {
        // Mock danych
        Long employeeId = 1L;
        Employee employee = new Employee();
        List<Position> positions = Collections.emptyList();

        when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);
        when(positionService.getAllPositions()).thenReturn(positions);

        // Wywołanie metody
        String viewName = employeeController.editEmployeeForm(employeeId, model);

        // Weryfikacje
        verify(employeeService).getEmployeeById(employeeId);
        verify(positionService).getAllPositions();
        verify(model).addAttribute("employee", employee);
        verify(model).addAttribute("positions", positions);
        assertEquals("employee_edit", viewName);
    }

    @Test
    void testUpdateEmployee() {
        // Mock danych
        Employee employee = new Employee();

        // Wywołanie metody
        String viewName = employeeController.updateEmployee(employee);

        // Weryfikacje
        verify(employeeService).saveEmployee(employee);
        assertEquals("redirect:/hr/employees", viewName);
    }

    @Test
    void testDeleteEmployee() {
        // Mock danych
        Long employeeId = 1L;

        // Wywołanie metody
        String viewName = employeeController.deleteEmployee(employeeId);

        // Weryfikacje
        verify(employeeService).deleteEmployee(employeeId);
        assertEquals("redirect:/hr/employees", viewName);
    }
}
