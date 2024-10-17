package com.example.erpsystem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="uslugi")
public class Usluga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_uslugi;

    private String nazwa;
    private String opis;
    private LocalTime czas_trwania;
    private BigDecimal cena;

    //konstruktory

    public Usluga(Long id_uslugi, String nazwa, String opis, LocalTime czas_trwania, BigDecimal cena) {
        this.id_uslugi = id_uslugi;
        this.nazwa = nazwa;
        this.opis = opis;
        this.czas_trwania = czas_trwania;
        this.cena = cena;
    }

    public Usluga() {
    }

    //gettery

    public Long getId_uslugi() {
        return id_uslugi;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public LocalTime getCzas_trwania() {
        return czas_trwania;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setId_uslugi(Long id_uslugi) {
        this.id_uslugi = id_uslugi;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCzas_trwania(LocalTime czas_trwania) {
        this.czas_trwania = czas_trwania;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
