package com.example.erpsystem;
import jakarta.persistence.*;


@Entity
@Table(name = "klienci")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_klient;

    private String imie;
    private String nazwisko;
    private String stanowisko;
    private String email;
    private String numer_telefonu;
    private String uwagi;

    //konstruktory
    public Klient(Long id_klient, String imie, String nazwisko, String stanowisko, String email, String numer_telefonu, String uwagi) {
        this.id_klient = id_klient;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.email = email;
        this.numer_telefonu = numer_telefonu;
        this.uwagi = uwagi;
    }

    public Klient() {
    }

    //gettery

    public Long getId_klient() {
        return id_klient;
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

    public String getUwagi() {
        return uwagi;
    }

    //settery

    public void setId_klient(Long id_klient) {
        this.id_klient = id_klient;
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

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }
}

