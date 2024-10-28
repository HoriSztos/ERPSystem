package com.example.erpsystem;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_employee;

    private String name;
    private String surname;
    private String position;
    private String email;
    private String phone_number;
    private LocalDate date_of_employment;

@OneToMany(mappedBy = "employee")
    private List<Sale> sale;


//konstruktory
    public Employee(Long id_employee, String name, String surname, String position, String email, String phone_number, LocalDate date_of_employment) {
        this.id_employee = id_employee;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phone_number = phone_number;
        this.date_of_employment = date_of_employment;
    }

    public Employee() {
    }
//gettery


    public Long getId_employee() {
        return id_employee;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public LocalDate getDate_of_employment() {
        return date_of_employment;
    }

    public List<Sale> getSale() {
        return sale;
    }
//settery


    public void setId_employee(Long id_employee) {
        this.id_employee = id_employee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setDate_of_employment(LocalDate date_of_employment) {
        this.date_of_employment = date_of_employment;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }
}
