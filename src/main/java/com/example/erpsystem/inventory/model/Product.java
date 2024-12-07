package com.example.erpsystem.inventory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="purchase_price")
    private float purchase_price;

    @Column(name="sale_price")
    private float sale_price;

    @Column(name="stock")
    private int stock;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    //konstruktory
    public Product(Long id, String name, String description, float purchase_price, float sale_price, int stock, LocalDate expiration_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.purchase_price = purchase_price;
        this.sale_price = sale_price;
        this.stock = stock;
        this.expirationDate = expiration_date;
    }

    public Product() {
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

    public float getPurchase_price() {
        return purchase_price;
    }

    public float getSale_price() {
        return sale_price;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getExpiration_date() {return expirationDate;}

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

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setExpiration_date(LocalDate expiration_date) {this.expirationDate = expiration_date;}


}
