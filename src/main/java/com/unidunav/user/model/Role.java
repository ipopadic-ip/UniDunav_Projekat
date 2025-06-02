package com.unidunav.user.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv; // npr. "ADMIN", "PROFESOR", "STUDENT"
    
    private boolean aktivna = true; // logičko brisanje

    public Role(String naziv) {
        this.naziv = naziv;
        this.aktivna = true;
    }
    
    public Role() {}

//    public Role(String naziv) {
//        this.naziv = naziv;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isAktivna() { return aktivna; }
    public void setAktivna(boolean aktivna) { this.aktivna = aktivna; }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getAuthority() {
        return naziv;
    }
}
