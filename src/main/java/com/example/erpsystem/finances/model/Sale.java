package com.example.erpsystem.finances.model;
import com.example.erpsystem.model.Client;
import com.example.erpsystem.model.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;

    private LocalDate date_of_sale;
    private String payment_method;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name="id_klient")
    private Client client;

    @ManyToOne
    @JoinColumn(name="id_pracownik")
    private Employee employee;

    //konstruktory

    public Sale(Long id_sale, LocalDate date_of_sale, String payment_method, BigDecimal total, Client client, Employee employee) {
        this.id_sale = id_sale;
        this.date_of_sale = date_of_sale;
        this.payment_method = payment_method;
        this.total = total;
        this.client = client;
        this.employee = employee;
    }

    public Sale() {
    }

    //gettery

    public Long getId_sale() {
        return id_sale;
    }

    public LocalDate getDate_of_sale() {
        return date_of_sale;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Client getClient() {return client;}

    public Employee getEmployee() {return employee;}

    //settery
    public void setId_sale(Long id_sale) {
        this.id_sale = id_sale;
    }

    public void setDate_of_sale(LocalDate date_of_sale) {
        this.date_of_sale = date_of_sale;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
