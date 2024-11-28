package com.example.erpsystem.finances.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name="description")
    private String description;

    //konstruktory


    public Transaction(int id, LocalDate date, double amount, TransactionType type, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Transaction() {
    }

    //gettery

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    //settery


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
