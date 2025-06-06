package com.unidunav.sluzbenik.model;

import com.unidunav.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class IznajmljivanjeKnjige {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Knjiga knjiga;

    @ManyToOne
    private User korisnik;

    private int brojPrimeraka;
    private LocalDate datum;
    
    @Column(nullable = false)
    private boolean vraceno = false;


    public IznajmljivanjeKnjige() {}

    public IznajmljivanjeKnjige(Knjiga knjiga, User korisnik, int brojPrimeraka, LocalDate datum) {
        this.knjiga = knjiga;
        this.korisnik = korisnik;
        this.brojPrimeraka = brojPrimeraka;
        this.datum = datum;
        this.vraceno = false;
    }
    

	public boolean isVraceno() {
		return vraceno;
	}

	public void setVraceno(boolean vraceno) {
		this.vraceno = vraceno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public User getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(User korisnik) {
		this.korisnik = korisnik;
	}

	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}

	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

    
}
