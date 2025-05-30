package com.unidunav.profesor.model;

import com.unidunav.user.model.User;

import jakarta.persistence.*;

@Entity
public class Profesor {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

//    private String ime;
//    private String prezime;

    @Column(length = 3000)
    private String biografija;
    
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

//    public String getIme() {
//        return ime;
//    }
//
//    public void setIme(String ime) {
//        this.ime = ime;
//    }
//
//    public String getPrezime() {
//        return prezime;
//    }
//
//    public void setPrezime(String prezime) {
//        this.prezime = prezime;
//    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
