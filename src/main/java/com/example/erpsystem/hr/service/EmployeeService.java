package com.example.erpsystem.hr.service;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee  getEmployeeById (Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pracownik nie znaleziony"));
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public void updateEmployeesPosition(Long positionId) {
        List<Employee> employees = employeeRepository.findByPositionId(positionId);
        for (Employee employee : employees) {
            employee.setPosition(null);
        }
        employeeRepository.saveAll(employees);
    }
}
