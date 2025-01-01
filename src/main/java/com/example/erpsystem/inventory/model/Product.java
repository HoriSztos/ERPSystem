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
    private float purchasePrice;

    @Column(name="sale_price")
    private float salePrice;

    @Column(name="stock")
    private int stock;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name = "minimal_stock", nullable = false)
    private int minimalStock;


    //konstruktory
    public Product(Long id, String name, String description, float purchasePrice, float salePrice, int stock, LocalDate expirationDate, int minimalStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.expirationDate = expirationDate;
        this.minimalStock = minimalStock;
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

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getExpirationDate() {return expirationDate;}

    public int getMinimalStock() { return minimalStock; }

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

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setExpirationDate(LocalDate expirationDate) {this.expirationDate = expirationDate;}

    public void setMinimalStock(int minimalStock) { this.minimalStock = minimalStock;}

}
