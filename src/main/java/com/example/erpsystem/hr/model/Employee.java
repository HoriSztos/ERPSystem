package com.example.erpsystem.hr.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phone_number;
    @Column(name="date_of_employment")
    private LocalDate date_of_employment;
    @Column(name="salary")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;



//konstruktory
    public Employee(Long id, String name, String surname, Position position, String email, String phone_number, LocalDate date_of_employment, Double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phone_number = phone_number;
        this.date_of_employment = date_of_employment;
        this.salary = salary;
    }

    public Employee() {
    }
//gettery


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Position getPosition() {
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

    public Double getSalary() { return salary; }


    //settery

    public void setId(Long id_employee) {
        this.id = id_employee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(Position position) {
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

    public void setSalary(Double salary) { this.salary = salary; }
}
