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
    private PrimerakKnjige primerakKnjige;

    @ManyToOne
    private User korisnik;

    
    private LocalDate datum;
    
    
    private LocalDate vraceno;


    public IznajmljivanjeKnjige() {}


	public IznajmljivanjeKnjige(Long id, PrimerakKnjige primerakKnjige, User korisnik, LocalDate datum,
			LocalDate vraceno) {
		super();
		this.id = id;
		this.primerakKnjige = primerakKnjige;
		this.korisnik = korisnik;
		this.datum = datum;
		this.vraceno = vraceno;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public PrimerakKnjige getPrimerakKnjige() {
		return primerakKnjige;
	}


	public void setPrimerakKnjige(PrimerakKnjige primerakKnjige) {
		this.primerakKnjige = primerakKnjige;
	}


	public User getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(User korisnik) {
		this.korisnik = korisnik;
	}


	public LocalDate getDatum() {
		return datum;
	}


	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}


	public LocalDate getVraceno() {
		return vraceno;
	}


	public void setVraceno(LocalDate vraceno) {
		this.vraceno = vraceno;
	}


	
}