package com.example.erpsystem.treatments.repository;

import com.example.erpsystem.treatments.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
}
