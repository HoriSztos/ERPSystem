package com.example.erpsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name;
    private String description;
    private float purchase_price;
    private float sale_price;
    private int stock;
    private LocalDate expiration_date;

    //konstruktory
    public Product(Long id_product, String name, String description, float purchase_price, float sale_price, int stock, LocalDate expiration_date) {
        this.id_product = id_product;
        this.name = name;
        this.description = description;
        this.purchase_price = purchase_price;
        this.sale_price = sale_price;
        this.stock = stock;
        this.expiration_date = expiration_date;
    }

    public Product() {
    }

    //gettery

    public Long getId_product() {
        return id_product;
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

    public LocalDate getExpiration_date() {return expiration_date;}

    //settery

    public void setId_product(Long id_product) {
        this.id_product = id_product;
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

    public void setExpiration_date(LocalDate expiration_date) {this.expiration_date = expiration_date;}


}
