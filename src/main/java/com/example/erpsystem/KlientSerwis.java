package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientSerwis {
    private final KlientRepozytorium klientRepozytorium;

    @Autowired
    public KlientSerwis(KlientRepozytorium klientRepozytorium){
        this.klientRepozytorium=klientRepozytorium;
    }
    public List<Klient> getAllClients(){
        return klientRepozytorium.findAll();
    }
    public Klient getClientById(Long id){
        return klientRepozytorium.findById(id).orElseThrow(() -> new IllegalArgumentException("Klient nie znaleziony"));
    }
    public Klient saveClient(Klient klient){
        return  klientRepozytorium.save(klient);
    }
    public  void deleteClient(Long id){
        klientRepozytorium.deleteById(id);
    }
}
