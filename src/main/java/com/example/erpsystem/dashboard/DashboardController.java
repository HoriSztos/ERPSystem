package com.example.erpsystem.dashboard;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Pobieranie informacji o zalogowanym użytkowniku
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Przekazywanie danych do widoku
        model.addAttribute("username", username);
        return "dashboard"; // wskazanie widoku Thymeleaf o nazwie "dashboard.html"
    }
}
