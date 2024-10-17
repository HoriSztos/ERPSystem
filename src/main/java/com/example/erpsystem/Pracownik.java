package com.example.erpsystem;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pracownicy")
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pracownik;

    private String imie;
    private String nazwisko;
    private String stanowisko;
    private String email;
    private String numer_telefonu;
    private LocalDate data_zatrudnienia;

@OneToMany(mappedBy = "pracownik")
    private List<Sprzedaz> sprzedaz;


//konstruktory
    public Pracownik(Long id_pracownik, String imie, String nazwisko, String stanowisko, String email, String numer_telefonu, LocalDate data_zatrudnienia) {
        this.id_pracownik = id_pracownik;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.email = email;
        this.numer_telefonu = numer_telefonu;
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public Pracownik() {
    }
//gettery


    public Long getId_pracownik() {
        return id_pracownik;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public String getEmail() {
        return email;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public LocalDate getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public List<Sprzedaz> getSprzedaz() {
        return sprzedaz;
    }
//settery


    public void setId_pracownik(Long id_pracownik) {
        this.id_pracownik = id_pracownik;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    public void setData_zatrudnienia(LocalDate data_zatrudnienia) {
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public void setSprzedaz(List<Sprzedaz> sprzedaz) {
        this.sprzedaz = sprzedaz;
    }
}
