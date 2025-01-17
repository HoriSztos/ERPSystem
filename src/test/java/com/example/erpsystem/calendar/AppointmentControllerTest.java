package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.service.AppointmentService;
import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.service.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private TreatmentService treatmentService;

    @Mock
    private Model model;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalendarHome() {
        // Mock danych
        LocalDateTime startOfMonth = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime endOfMonth = LocalDateTime.of(2025, 2, 1, 0, 0);

        Appointment appointment = new Appointment();
        appointment.setStartTime(startOfMonth);
        appointment.setEndTime(startOfMonth.plusHours(1));
        List<Appointment> mockAppointments = List.of(appointment);

        when(appointmentService.getAppointmentsInRange(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(mockAppointments);

        // Wywołanie metody
        String viewName = appointmentController.calendarHome(model);

        // Weryfikacje
        verify(appointmentService).getAppointmentsInRange(any(LocalDateTime.class), any(LocalDateTime.class));
        verify(model).addAttribute(eq("appointments"), eq(mockAppointments));
        assertEquals("calendar_home", viewName);
    }


    @Test
    void testAddAppointmentForm() {
        // Mock danych
        List<Treatment> treatmentList = new ArrayList<>();
        Treatment treatment = new Treatment();
        treatment.setId(1L);
        treatment.setName("Test Treatment");
        treatmentList.add(treatment);

        when(treatmentService.findAll()).thenReturn(treatmentList);

        // Wywołanie metody
        String viewName = appointmentController.addAppointmentForm(model);

        // Weryfikacje
        verify(treatmentService).findAll();
        verify(model).addAttribute(eq("treatmentList"), eq(treatmentList));
        verify(model).addAttribute(eq("appointment"), any(Appointment.class));
        assertEquals("calendar_add", viewName);
    }

    @Test
    void testSaveAppointment() {
        // Mock danych
        Appointment appointment = new Appointment();

        // Wywołanie metody
        String viewName = appointmentController.saveAppointment(appointment);

        // Weryfikacje
        verify(appointmentService).saveAppointment(eq(appointment));
        assertEquals("redirect:/calendar", viewName);
    }

    @Test
    void testEditAppointmentForm() {
        // Mock danych
        Long id = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(id);

        when(appointmentService.getAppointmentById(id)).thenReturn(appointment);

        // Wywołanie metody
        String viewName = appointmentController.editAppointmentForm(id, model);

        // Weryfikacje
        verify(appointmentService).getAppointmentById(eq(id));
        verify(model).addAttribute(eq("appointment"), eq(appointment));
        assertEquals("calendar_edit", viewName);
    }

    @Test
    void testUpdateAppointment() {
        // Mock danych
        Appointment appointment = new Appointment();

        // Wywołanie metody
        String viewName = appointmentController.updateAppointment(appointment);

        // Weryfikacje
        verify(appointmentService).saveAppointment(eq(appointment));
        assertEquals("redirect:/calendar", viewName);
    }

    @Test
    void testDeleteAppointment() {
        // Mock danych
        Long id = 1L;

        // Wywołanie metody
        String viewName = appointmentController.deleteAppointment(id);

        // Weryfikacje
        verify(appointmentService).deleteAppointment(eq(id));
        assertEquals("redirect:/calendar", viewName);
    }
}
