package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void testFindAppointmentsWithinRange() {
        // Przygotowanie danych testowych
        Appointment appointment1 = new Appointment();
        appointment1.setStartTime(LocalDateTime.of(2025, 1, 10, 10, 0));
        appointment1.setEndTime(LocalDateTime.of(2025, 1, 10, 11, 0));

        Appointment appointment2 = new Appointment();
        appointment2.setStartTime(LocalDateTime.of(2025, 1, 15, 14, 0));
        appointment2.setEndTime(LocalDateTime.of(2025, 1, 15, 15, 0));

        Appointment appointment3 = new Appointment();
        appointment3.setStartTime(LocalDateTime.of(2025, 2, 5, 9, 0));
        appointment3.setEndTime(LocalDateTime.of(2025, 2, 5, 10, 0));

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);

        // Wywołanie metody repozytorium
        LocalDateTime start = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 23, 59);
        List<Appointment> appointments = appointmentRepository.findAppointmentsWithinRange(start, end);

        // Weryfikacja wyników
        assertEquals(2, appointments.size());
        assertEquals(appointment1.getStartTime(), appointments.get(0).getStartTime());
        assertEquals(appointment2.getStartTime(), appointments.get(1).getStartTime());
    }
}
