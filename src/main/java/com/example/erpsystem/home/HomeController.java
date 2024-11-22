package com.example.erpsystem.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        return "home"; // wskazanie widoku Thymeleaf o nazwie "home.html"
    }
}
