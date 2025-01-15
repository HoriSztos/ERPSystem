package com.example.erpsystem.hr.repository;

import com.example.erpsystem.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPositionId(Long positionId);
}
