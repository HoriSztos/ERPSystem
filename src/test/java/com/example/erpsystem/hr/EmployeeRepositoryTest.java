package com.example.erpsystem.hr;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.EmployeeRepository;
import com.example.erpsystem.hr.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository; // Upewnij się, że repozytorium pozycji jest poprawnie zainicjalizowane

    @Test
    void testFindByPositionId() {
        // Przygotowanie danych testowych
        Position position1 = new Position();
        position1.setName("Manager");
        position1 = positionRepository.save(position1); // Zapisanie pozycji do bazy danych

        Position position2 = new Position();
        position2.setName("Developer");
        position2 = positionRepository.save(position2); // Zapisanie pozycji do bazy danych

        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setPosition(position1);

        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setPosition(position1);

        Employee employee3 = new Employee();
        employee3.setName("Alice Brown");
        employee3.setPosition(position2);

        employeeRepository.save(employee1); // Zapisanie pracowników do bazy danych
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        // Wywołanie metody repozytorium
        List<Employee> employeesInPosition1 = employeeRepository.findByPositionId(position1.getId());
        List<Employee> employeesInPosition2 = employeeRepository.findByPositionId(position2.getId());

        // Weryfikacje
        assertEquals(2, employeesInPosition1.size());
        assertEquals("John Doe", employeesInPosition1.get(0).getName());
        assertEquals("Jane Smith", employeesInPosition1.get(1).getName());

        assertEquals(1, employeesInPosition2.size());
        assertEquals("Alice Brown", employeesInPosition2.get(0).getName());
    }
}
