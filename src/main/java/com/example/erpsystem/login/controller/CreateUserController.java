package com.example.erpsystem.login.controller;

import com.example.erpsystem.login.model.Role;
import com.example.erpsystem.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class CreateUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam Role role,
                             Model model) {
        userService.createUser(username, password, role);

        model.addAttribute("username", username);
        model.addAttribute("role", role.name());

        return "result";
    }
}