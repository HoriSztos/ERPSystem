package com.example.erpsystem.calendar;

import com.example.erpsystem.calendar.model.Appointment;
import com.example.erpsystem.calendar.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class CalendarApiController {
    private final AppointmentService appointmentService;

    public CalendarApiController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/events")
    public List<Map<String, Object>> getAppointments(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end
    ) {
        List<Appointment> appointments = appointmentService.getAppointmentsInRange(
                start != null ? start : LocalDateTime.now().minusMonths(1),
                end != null ? end : LocalDateTime.now().plusMonths(1)
        );

        return appointments.stream().map(appointment -> {
            Map<String, Object> event = new HashMap<>();
            event.put("id", appointment.getId());
            event.put("name", appointment.getName());
            event.put("start", appointment.getStartTime());
            event.put("end", appointment.getEndTime());
            event.put("description", appointment.getDescription());
            event.put("client", appointment.getClient());
            return event;
        }).toList();
    }
}
