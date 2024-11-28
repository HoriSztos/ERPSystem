package com.example.erpsystem.calendar.repository;

import com.example.erpsystem.calendar.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.startTime BETWEEN :start AND :end ORDER BY a.startTime")
    List<Appointment> findAppointmentsWithinRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}