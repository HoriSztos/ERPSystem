package com.example.erpsystem.login.controller;

import com.example.erpsystem.login.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        adminService.createUser(username, password, role);
        return ResponseEntity.ok("User created successfully");
    }
}
