package com.example.erpsystem;
import jakarta.persistence.*;

@Entity
@Table(name="produkty")
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produkt;
    private String nazwa;
    private String opis;
    private float cena_zakupu;
    private float cena_sprzedazy;
    private int stan_magazynowy;

    //konstruktory
    public Produkt(Long id_produkt, String nazwa, String opis, float cena_zakupu, float cena_sprzedazy, int stan_magazynowy) {
        this.id_produkt = id_produkt;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena_zakupu = cena_zakupu;
        this.cena_sprzedazy = cena_sprzedazy;
        this.stan_magazynowy = stan_magazynowy;
    }

    public Produkt() {
    }

    //gettery

    public Long getId_produkt() {
        return id_produkt;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public float getCena_zakupu() {
        return cena_zakupu;
    }

    public float getCena_sprzedazy() {
        return cena_sprzedazy;
    }

    public int getStan_magazynowy() {
        return stan_magazynowy;
    }

    //settery

    public void setId_produkt(Long id_produkt) {
        this.id_produkt = id_produkt;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena_zakupu(float cena_zakupu) {
        this.cena_zakupu = cena_zakupu;
    }

    public void setCena_sprzedazy(float cena_sprzedazy) {
        this.cena_sprzedazy = cena_sprzedazy;
    }

    public void setStan_magazynowy(int stan_magazynowy) {
        this.stan_magazynowy = stan_magazynowy;
    }


}
