package com.example.erpsystem.calendar.model;

import com.example.erpsystem.treatments.model.Treatment;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "starttime")
    private LocalDateTime startTime;

    @Column(name = "endtime")
    private LocalDateTime endTime;

    @Column(name = "client")
    private String client;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    private String formattedTime;

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getClient() {
        return client;
    }
    public Treatment getTreatment() {
        return treatment;
    }
    public String getFormattedTime() {
        return formattedTime;
    }

    //settery

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    //konstruktory


    public Appointment(Long id, String name, String description, LocalDateTime startTime, LocalDateTime endTime, String client, Treatment treatment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.client = client;
        this.treatment = treatment;
    }

    public Appointment() {
    }
}
