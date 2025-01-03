package com.example.erpsystem.login.controller;

import com.example.erpsystem.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "createUser"; // widok createUser.html
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String role) {
        userService.createUser(username, password, role);
        return "result";
    }
}