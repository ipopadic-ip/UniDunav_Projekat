package com.unidunav.administrator.model;

import com.unidunav.user.model.User;

import jakarta.persistence.*;

@Entity
public class Administrator {
	 @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
	@OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String ime;
    private String prezime;

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
