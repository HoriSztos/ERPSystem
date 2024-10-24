package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprzedazSerwis {
    private final SprzedazRepozytorium sprzedazRepozytorium;
    @Autowired
    public SprzedazSerwis (SprzedazRepozytorium sprzedazRepozytorium){this.sprzedazRepozytorium=sprzedazRepozytorium;}

    public List<Sprzedaz> getAllSales(){
        return sprzedazRepozytorium.findAll();
    }
    public Sprzedaz getSaleByID(Long id){
        return sprzedazRepozytorium.findById(id).orElseThrow(() ->new IllegalArgumentException("Sprzedaży nie znaleziono"));
    }
    public Sprzedaz saveSale(Sprzedaz sprzedaz){
        return sprzedazRepozytorium.save(sprzedaz);
    }
    public void deleteSale(Long id){
        sprzedazRepozytorium.deleteById(id);
    }

}
