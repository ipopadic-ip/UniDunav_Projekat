package com.unidunav.sluzbenik.model;



import java.util.List;
import java.util.ArrayList;


import jakarta.persistence.*;

@Entity
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String autor;
    private String zanr;
    private int godinaIzdavanja;
    private String izdavac;
    private int brojPrimeraka;
    @OneToMany(mappedBy = "knjiga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrimerakKnjige> primerci = new ArrayList<>();

    
	public List<PrimerakKnjige> getPrimerci() {
		return primerci;
	}
	public void setPrimerci(List<PrimerakKnjige> primerci) {
		this.primerci = primerci;
	}
	public Knjiga() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Knjiga(Long id, String naziv, String autor, String zanr, int godinaIzdavanja, String izdavac,
			int brojPrimeraka, List<PrimerakKnjige> primerci) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.autor = autor;
		this.zanr = zanr;
		this.godinaIzdavanja = godinaIzdavanja;
		this.izdavac = izdavac;
		this.brojPrimeraka = brojPrimeraka;
		this.primerci = primerci;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}
	public void setGodinaIzdavanja(int godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}
	public String getIzdavac() {
		return izdavac;
	}
	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}
	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}
	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}
	
    

}