package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktSerwis {
    private final ProduktRepozytorium produktRepozytorium;

    @Autowired
    public ProduktSerwis(ProduktRepozytorium produktRepozytorium){this.produktRepozytorium=produktRepozytorium;}
    public List<Produkt> getAllProducts(){
        return produktRepozytorium.findAll();
    }
    public Produkt getProductById(Long id){
        return produktRepozytorium.findById(id).orElseThrow(() -> new IllegalArgumentException("Produkt nie znaleziony"));
    }
    public Produkt saveProduct(Produkt produkt){
        return produktRepozytorium.save(produkt);
    }
    public void deleteProduct(Long id){
        produktRepozytorium.deleteById(id);
    }
}
