package com.example.erpsystem.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // wskazanie widoku Thymeleaf o nazwie "dashboard.html"
    }
}
