package com.example.erpsystem.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mockowanie SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testShowDashboard() {
        // Mock danych
        String username = "testUser";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);

        // Wywołanie metody
        String viewName = dashboardController.showDashboard(model);

        // Weryfikacje
        verify(securityContext).getAuthentication();
        verify(authentication).getName();
        verify(model).addAttribute("username", username);
        assertEquals("dashboard", viewName);
    }
}
