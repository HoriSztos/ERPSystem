package com.example.erpsystem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="sprzedaz")
public class Sprzedaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sprzedaz;

    private LocalDate data_sprzedazy;
    private String metod_platnosci;
    private BigDecimal kwota;

    @ManyToOne
    @JoinColumn(name="id_klient")
    private Klient klient;

    @ManyToOne
    @JoinColumn(name="id_pracownik")
    private Pracownik pracownik;

    //konstruktory

    public Sprzedaz(Long id_sprzedaz, LocalDate data_sprzedazy, String metod_platnosci, BigDecimal kwota, Klient klient, Pracownik pracownik) {
        this.id_sprzedaz = id_sprzedaz;
        this.data_sprzedazy = data_sprzedazy;
        this.metod_platnosci = metod_platnosci;
        this.kwota = kwota;
        this.klient = klient;
        this.pracownik = pracownik;
    }

    public Sprzedaz() {
    }

    //gettery

    public Long getId_sprzedaz() {
        return id_sprzedaz;
    }

    public LocalDate getData_sprzedazy() {
        return data_sprzedazy;
    }

    public String getMetod_platnosci() {
        return metod_platnosci;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public Klient getKlient() {
        return klient;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    //settery
    public void setId_sprzedaz(Long id_sprzedaz) {
        this.id_sprzedaz = id_sprzedaz;
    }

    public void setData_sprzedazy(LocalDate data_sprzedazy) {
        this.data_sprzedazy = data_sprzedazy;
    }

    public void setMetod_platnosci(String metod_platnosci) {
        this.metod_platnosci = metod_platnosci;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
}
