package com.example.erpsystem.login.service;

import com.example.erpsystem.login.repository.UserRepository;
import com.example.erpsystem.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String username, String password, String roleName) {
        if (roleName == null) {
            throw new RuntimeException("Role '" + roleName + "' not found!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleName);

        userRepository.save(user);
    }
}
