package com.example.erpsystem.hr.service;

import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
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
}
