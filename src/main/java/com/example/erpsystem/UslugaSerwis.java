package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UslugaSerwis {
    private final UslugaRepozytorium uslugaRepozytorium;

    @Autowired
    public UslugaSerwis (UslugaRepozytorium uslugaRepozytorium) { this.uslugaRepozytorium=uslugaRepozytorium; }

    public List<Usluga> getAllServices(){
        return uslugaRepozytorium.findAll();
    }
    public Usluga getServiceByID(Long id){
        return uslugaRepozytorium.findById(id).orElseThrow(() ->new IllegalArgumentException("Usługi nie znaleziono"));
    }
    public Usluga saveService(Usluga usluga){
        return uslugaRepozytorium.save(usluga);
    }
    public void deleteService(Long id){
        uslugaRepozytorium.deleteById(id);
    }
}
