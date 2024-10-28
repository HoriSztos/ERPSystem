package com.example.erpsystem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="treatments")
public class Treatment { //Uzycie treatment zamiast service z powodu konfliktu z adnotacją Service
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_treatment;

    private String name;
    private String description;
    private LocalTime duration;
    private BigDecimal price;

    //konstruktory

    public Treatment(Long id_treatment, String name, String description, LocalTime duration, BigDecimal price) {
        this.id_treatment = id_treatment;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Treatment() {
    }

    //gettery

    public Long getId_treatment() {
        return id_treatment;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //settery
    public void setId_treatment(Long id_service) {
        this.id_treatment = id_service;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
