package com.example.erpsystem.login.security;

import com.example.erpsystem.login.repository.UserRepository;
import com.example.erpsystem.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNameNative(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //String roleName = (user.getRole() != null) ? user.getRole() : "USER";

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword()) // Hasło użytkownika pobierane z bazy danych.
                .roles(user.getRole())
                .build();
    }
}
