package com.example.erpsystem.login;

import com.example.erpsystem.login.controller.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowLogin() {
        // Wywołanie metody
        String viewName = loginController.showLogin();

        // Weryfikacje
        assertEquals("login", viewName);
    }
}