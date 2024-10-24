package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracownikSerwis {
    private final PracownikRepozytorium pracownikRepozytorium;

    @Autowired
    public PracownikSerwis (PracownikRepozytorium pracownikRepozytorium){this.pracownikRepozytorium=pracownikRepozytorium;}

    public List<Pracownik> getAllEmployees(){
        return pracownikRepozytorium.findAll();
    }
    public Pracownik  getEmployeeById (Long id){
        return pracownikRepozytorium.findById(id).orElseThrow(() -> new IllegalArgumentException("Pracownik nie znaleziony"));
    }
    public Pracownik saveEmployee(Pracownik pracownik){
        return pracownikRepozytorium.save(pracownik);
    }
    public void deleteEmployee(Long id){
        pracownikRepozytorium.deleteById(id);
    }
}
