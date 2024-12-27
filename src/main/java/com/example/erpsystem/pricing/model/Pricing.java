package com.example.erpsystem.pricing.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="pricing")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="business_operating")
    private BigDecimal businessOperating;
    @Column(name="employees")
    private BigDecimal employees;
    @Column(name="equipment")
    private BigDecimal equipment;
    @Column(name="products")
    private BigDecimal products;
    @Column(name="training")
    private BigDecimal training;
    @Column(name="income")
    private BigDecimal income;
    @Column(name="taxes")
    private BigDecimal taxes;
    @Column(name="man_hours")
    private Long manHours;
    @Column(name="man_hour_value")
    private BigDecimal manHourValue;




    //gettery

    public Long getId() {
        return id;
    }

    public BigDecimal getBusinessOperating() {
        return businessOperating;
    }

    public BigDecimal getEmployees() {
        return employees;
    }

    public BigDecimal getEquipment() {
        return equipment;
    }

    public BigDecimal getProducts() {
        return products;
    }

    public BigDecimal getTraining() {
        return training;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public Long getManHours() {
        return manHours;
    }

    public BigDecimal getManHourValue() {
        return manHourValue;
    }
    //settery

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessOperating(BigDecimal businessOperating) {
        this.businessOperating = businessOperating;
    }

    public void setEmployees(BigDecimal employees) {
        this.employees = employees;
    }

    public void setEquipment(BigDecimal equipment) {
        this.equipment = equipment;
    }

    public void setProducts(BigDecimal products) {
        this.products = products;
    }

    public void setTraining(BigDecimal training) {
        this.training = training;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public void setManHours(Long manHours) {
        this.manHours = manHours;
    }

    public void setManHourValue(BigDecimal manHourValue) {
        this.manHourValue = manHourValue;
    }
    //konstruktory


    public Pricing() {
    }

    public Pricing(Long id, BigDecimal businessOperating, BigDecimal employees, BigDecimal equipment, BigDecimal products, BigDecimal training, BigDecimal income, BigDecimal taxes, Long manHours, BigDecimal manHourValue) {
        this.id = id;
        this.businessOperating = businessOperating;
        this.employees = employees;
        this.equipment = equipment;
        this.products = products;
        this.training = training;
        this.income = income;
        this.taxes = taxes;
        this.manHours = manHours;
        this.manHourValue = manHourValue;
    }
}
