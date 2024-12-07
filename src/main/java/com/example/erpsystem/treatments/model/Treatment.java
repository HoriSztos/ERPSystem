package com.example.erpsystem.treatments.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="treatments")
public class Treatment { //Uzycie treatment zamiast service z powodu konfliktu z adnotacją Service
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="duration")
    private LocalTime duration;

    @Column(name="price")
    private BigDecimal price;

    //konstruktory

    public Treatment(Long id, String name, String description, LocalTime duration, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Treatment() {
    }

    //gettery

    public Long getId() {
        return id;
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
    public void setId(Long id_service) {
        this.id = id_service;
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
