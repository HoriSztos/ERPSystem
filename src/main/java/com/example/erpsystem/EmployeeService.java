package com.example.erpsystem;

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
}
