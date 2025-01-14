package com.example.erpsystem.login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

//konstruktory

    public User() {
    }

    public User(Long id_user, String username, String password, Role role) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //gettery

    public Long getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() { return role; }

//settery

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
