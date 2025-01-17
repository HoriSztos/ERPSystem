package com.example.erpsystem.hr;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.EmployeeRepository;
import com.example.erpsystem.hr.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Mock danych
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        // Wywołanie metody
        List<Employee> result = employeeService.getAllEmployees();

        // Weryfikacje
        verify(employeeRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() {
        // Mock danych
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Wywołanie metody
        Employee result = employeeService.getEmployeeById(employeeId);

        // Weryfikacje
        verify(employeeRepository).findById(employeeId);
        assertEquals(employeeId, result.getId());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        // Mock danych
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Wywołanie metody i oczekiwanie wyjątku
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.getEmployeeById(employeeId);
        });

        // Weryfikacje
        verify(employeeRepository).findById(employeeId);
        assertEquals("Pracownik nie znaleziony", exception.getMessage());
    }

    @Test
    void testSaveEmployee() {
        // Mock danych
        Employee employee = new Employee();
        employee.setName("John Doe");

        when(employeeRepository.save(employee)).thenReturn(employee);

        // Wywołanie metody
        Employee result = employeeService.saveEmployee(employee);

        // Weryfikacje
        verify(employeeRepository).save(employee);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testDeleteEmployee() {
        // Mock danych
        Long employeeId = 1L;

        // Wywołanie metody
        employeeService.deleteEmployee(employeeId);

        // Weryfikacje
        verify(employeeRepository).deleteById(employeeId);
    }

    @Test
    void testUpdateEmployeesPosition() {
        // Mock danych
        Long positionId = 1L;
        Position position = new Position();
        position.setId(positionId);

        Employee employee1 = new Employee();
        employee1.setPosition(position);

        Employee employee2 = new Employee();
        employee2.setPosition(position);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findByPositionId(positionId)).thenReturn(employees);

        // Wywołanie metody
        employeeService.updateEmployeesPosition(positionId);

        // Weryfikacje
        for (Employee employee : employees) {
            assertNull(employee.getPosition());
        }
        verify(employeeRepository).findByPositionId(positionId);
        verify(employeeRepository).saveAll(employees);
    }
}

