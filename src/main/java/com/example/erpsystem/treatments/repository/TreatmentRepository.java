package com.example.erpsystem.treatments.repository;

import com.example.erpsystem.treatments.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
}
