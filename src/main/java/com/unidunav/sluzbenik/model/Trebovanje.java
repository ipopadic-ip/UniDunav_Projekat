package com.unidunav.sluzbenik.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.unidunav.user.model.User;

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
    private LocalDate datumTrebovanja;
    @ManyToOne
    private User sluzbenik;
    public Trebovanje() {}

    public Trebovanje(String nazivStavke, int kolicina, LocalDate datumTrebovanja) {
        this.nazivStavke = nazivStavke;
        this.kolicina = kolicina;
        this.datumTrebovanja = datumTrebovanja;
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

    public LocalDate getDatumTrebovanja() {
        return datumTrebovanja;
    }

    public void setDatumTrebovanja(LocalDate datumTrebovanja) {
        this.datumTrebovanja = datumTrebovanja;
    }
    
    public User getSluzbenik() {
        return sluzbenik;
    }

    public void setSluzbenik(User sluzbenik) {
        this.sluzbenik = sluzbenik;
    }
}
