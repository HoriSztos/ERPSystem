package com.example.erpsystem.login;

import com.example.erpsystem.login.controller.CreateUserController;
import com.example.erpsystem.login.model.Role;
import com.example.erpsystem.login.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CreateUserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private CreateUserController createUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowCreateUserForm() {
        // Wywołanie metody
        String viewName = createUserController.showCreateUserForm();

        // Weryfikacje
        assertEquals("createUser", viewName);
    }

    @Test
    void testCreateUser() {
        // Mock danych
        String username = "testUser";
        String password = "password";
        Role role = Role.ADMIN;

        // Wywołanie metody
        String viewName = createUserController.createUser(username, password, role, model);

        // Weryfikacje
        verify(userService).createUser(eq(username), eq(password), eq(role));
        verify(model).addAttribute("username", username);
        verify(model).addAttribute("role", role.name());
        assertEquals("result", viewName);
    }
}
