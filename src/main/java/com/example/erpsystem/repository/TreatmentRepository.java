package com.example.erpsystem.repository;

import com.example.erpsystem.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
}
