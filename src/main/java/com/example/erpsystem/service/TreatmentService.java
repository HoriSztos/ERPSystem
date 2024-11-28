package com.example.erpsystem.service;

import com.example.erpsystem.model.Treatment;
import com.example.erpsystem.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService (TreatmentRepository treatmentRepository) { this.treatmentRepository = treatmentRepository; }

    public List<Treatment> getAllServices(){
        return treatmentRepository.findAll();
    }
    public Treatment getServiceByID(Long id){
        return treatmentRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Usługi nie znaleziono"));
    }
    public Treatment saveService(Treatment treatment){
        return treatmentRepository.save(treatment);
    }
    public void deleteService(Long id){
        treatmentRepository.deleteById(id);
    }
}
