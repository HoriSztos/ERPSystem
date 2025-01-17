package com.example.erpsystem.login;

import com.example.erpsystem.login.model.Role;
import com.example.erpsystem.login.model.User;
import com.example.erpsystem.login.repository.UserRepository;
import com.example.erpsystem.login.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserSuccess() {
        // Mockowanie danych
        String username = "testuser";
        String password = "password123";
        Role role = Role.ADMIN;
        String encodedPassword = "encodedPassword123";

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // Wywołanie metody
        userService.createUser(username, password, role);

        // Weryfikacje
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(argThat(user ->
                user.getUsername().equals(username) &&
                        user.getPassword().equals(encodedPassword) &&
                        user.getRole() == role
        ));
    }

    @Test
    void testCreateUserWithNullRole() {
        // Mockowanie danych
        String username = "testuser";
        String password = "password123";

        // Wywołanie metody z oczekiwaniem na wyjątek
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.createUser(username, password, null)
        );

        // Weryfikacje
        assertEquals("Role 'null' not found!", exception.getMessage());
        verifyNoInteractions(userRepository);
    }
}
