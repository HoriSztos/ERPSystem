package com.example.erpsystem.hr.service;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.EmployeeRepository;
import com.example.erpsystem.hr.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;

    public PositionService(PositionRepository positionRepository, EmployeeRepository employeeRepository) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
    public void deletePosition(Long id){

        positionRepository.deleteById(id);
    }
    public List<Employee> findByPositionId(Long positionId) {
        return employeeRepository.findByPositionId(positionId); // Wykorzystanie repozytorium Employee
    }

}
