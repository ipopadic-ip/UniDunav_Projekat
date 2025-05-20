package com.unidunav.sluzbenik.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Trebovanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nazivStavke;

    @Column(nullable = false)
    private int kolicina;

    @Column(nullable = false)
    private LocalDate datum;

    public Trebovanje() {}

    public Trebovanje(String nazivStavke, int kolicina, LocalDate datum) {
        this.nazivStavke = nazivStavke;
        this.kolicina = kolicina;
        this.datum = datum;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public String getNazivStavke() {
        return nazivStavke;
    }

    public void setNazivStavke(String nazivStavke) {
        this.nazivStavke = nazivStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
