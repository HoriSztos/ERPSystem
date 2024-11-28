package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
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
        model.addAttribute("appointment", new Appointment());
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
