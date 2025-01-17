package com.example.erpsystem.login;

import com.example.erpsystem.login.model.Role;
import com.example.erpsystem.login.model.User;
import com.example.erpsystem.login.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Przygotowanie danych
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(Role.ADMIN); // Ustawienie roli

        // Zapisanie użytkownika
        User savedUser = userRepository.save(user);

        // Weryfikacja
        assertNotNull(savedUser.getId_user());
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("password123", savedUser.getPassword());
        assertEquals(Role.ADMIN, savedUser.getRole());
    }

    @Test
    void testFindById() {
        // Przygotowanie danych
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(Role.ADMIN); // Ustawienie roli

        // Zapisanie użytkownika
        User savedUser = userRepository.save(user);

        // Pobranie użytkownika po ID
        Optional<User> foundUser = userRepository.findById(savedUser.getId_user());

        // Weryfikacja
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testFindByUsername() {
        // Przygotowanie danych
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(Role.ADMIN); // Ustawienie roli

        // Zapisanie użytkownika
        userRepository.save(user);

        // Pobranie użytkownika po nazwie użytkownika
        User foundUser = userRepository.findByUsername("testuser");

        // Weryfikacja
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        // Przygotowanie danych
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(Role.ADMIN); // Ustawienie roli

        // Zapisanie użytkownika
        User savedUser = userRepository.save(user);

        // Usunięcie użytkownika
        userRepository.deleteById(savedUser.getId_user());

        // Weryfikacja
        Optional<User> foundUser = userRepository.findById(savedUser.getId_user());
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByUsername_NotFound() {
        // Pobranie użytkownika po nazwie użytkownika, który nie istnieje
        User foundUser = userRepository.findByUsername("nonexistentuser");

        // Weryfikacja
        assertNull(foundUser);
    }
}
