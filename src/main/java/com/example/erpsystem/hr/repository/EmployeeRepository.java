package com.example.erpsystem.hr.repository;

import com.example.erpsystem.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
