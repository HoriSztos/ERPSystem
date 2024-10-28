package com.example.erpsystem.login;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //konstruktory
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    //gettery
    public Long getId() { return id; }
    public String getName() { return name; }

    //settery
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}