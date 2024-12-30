package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.service.AppointmentService;
import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.service.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final TreatmentService treatmentService; // Dodana obsługa zabiegów

    public AppointmentController(AppointmentService appointmentService, TreatmentService treatmentService) {
        this.appointmentService = appointmentService;
        this.treatmentService = treatmentService; // Inicjalizacja zabiegów
    }

    @GetMapping
    public String calendarHome(Model model) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0);
        LocalDateTime endOfMonth = now.plusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0);

        List<Appointment> appointments = appointmentService.getAppointmentsInRange(startOfMonth, endOfMonth);
        model.addAttribute("appointments", appointments);
        return "calendar_home";
    }

    @GetMapping("/add")
    public String addAppointmentForm(Model model) {
        // Pobranie listy dostępnych zabiegów
        List<Treatment> treatmentList = treatmentService.findAll();
        model.addAttribute("treatmentList", treatmentList); // Dodaj listę zabiegów do modelu
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("treatment", new Treatment());
        // Dodanie logowania do konsoli
        if (treatmentList != null) {
            treatmentList.forEach(treatment -> System.out.println("Treatment ID: " + treatment.getId() + ", Name: " + treatment.getName()));
        }
        return "calendar_add";
    }

    @PostMapping("/add")
    public String saveAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/calendar";
    }

    @GetMapping("/edit/{id}")
    public String editAppointmentForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "calendar_edit";
    }

    @PostMapping("/edit")
    public String updateAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/calendar";
    }

    @PostMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/calendar";
    }
}
