package com.example.erpsystem;
import jakarta.persistence.*;


@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;

    private String name;
    private String surname;
    private String email;
    private String phone_number;
    private String notes;

    //konstruktory
    public Client(Long id_client, String name, String surname, String email, String phone_number, String notes) {
        this.id_client = id_client;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.notes = notes;
    }

    public Client() {
    }

    //gettery

    public Long getId_client() {
        return id_client;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getNotes() {
        return notes;
    }

    //settery

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

