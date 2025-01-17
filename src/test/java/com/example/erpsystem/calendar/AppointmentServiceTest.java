package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.repository.AppointmentRepository;
import com.example.erpsystem.calendar.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAppointmentsInRange() {
        // Mock danych
        LocalDateTime start = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 23, 59);
        List<Appointment> mockAppointments = Arrays.asList(
                new Appointment(), new Appointment()
        );

        when(appointmentRepository.findAppointmentsWithinRange(start, end)).thenReturn(mockAppointments);

        // Wywołanie metody
        List<Appointment> appointments = appointmentService.getAppointmentsInRange(start, end);

        // Weryfikacje
        verify(appointmentRepository).findAppointmentsWithinRange(start, end);
        assertEquals(2, appointments.size());
    }

    @Test
    void testSaveAppointment() {
        // Mock danych
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        // Wywołanie metody
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);

        // Weryfikacje
        verify(appointmentRepository).save(appointment);
        assertEquals(1L, savedAppointment.getId());
    }

    @Test
    void testDeleteAppointment() {
        // Mock danych
        Long id = 1L;

        // Wywołanie metody
        appointmentService.deleteAppointment(id);

        // Weryfikacje
        verify(appointmentRepository).deleteById(id);
    }

    @Test
    void testGetAppointmentById() {
        // Mock danych
        Long id = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(id);

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        // Wywołanie metody
        Appointment foundAppointment = appointmentService.getAppointmentById(id);

        // Weryfikacje
        verify(appointmentRepository).findById(id);
        assertEquals(id, foundAppointment.getId());
    }

    @Test
    void testGetAppointmentById_NotFound() {
        // Mock danych
        Long id = 1L;

        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        // Wywołanie metody
        Appointment foundAppointment = appointmentService.getAppointmentById(id);

        // Weryfikacje
        verify(appointmentRepository).findById(id);
        assertNull(foundAppointment);
    }

    @Test
    void testGetAllAppointments() {
        // Mock danych
        List<Appointment> mockAppointments = Arrays.asList(
                new Appointment(), new Appointment(), new Appointment()
        );

        when(appointmentRepository.findAll()).thenReturn(mockAppointments);

        // Wywołanie metody
        List<Appointment> appointments = appointmentService.getAllAppointments();

        // Weryfikacje
        verify(appointmentRepository).findAll();
        assertEquals(3, appointments.size());
    }
}
