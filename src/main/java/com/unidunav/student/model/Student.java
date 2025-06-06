package com.unidunav.student.model;

import com.unidunav.user.model.User;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    private Long id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String brojIndeksa;
    private int godinaUpisa;
    private double prosecnaOcena = 0.0;
    private int ukupnoEcts = 0;
    
    private String slikaPath;

    public String getSlikaPath() {
        return slikaPath;
    }

    public void setSlikaPath(String slikaPath) {
        this.slikaPath = slikaPath;
    }


    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public int getUkupnoEcts() {
        return ukupnoEcts;
    }

    public void setUkupnoEcts(int ukupnoEcts) {
        this.ukupnoEcts = ukupnoEcts;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
