package com.unidunav.sluzbenik.model;

import jakarta.persistence.*;

@Entity
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String autor;
    private int godinaIzdavanja;

    private int brojPrimeraka;      // ukupno
    private int brojDostupnih;      // preostalo

    public Knjiga() {}

    public Knjiga(String naziv, String autor, int godinaIzdavanja, int brojPrimeraka) {
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdavanja = godinaIzdavanja;
        this.brojPrimeraka = brojPrimeraka;
        this.brojDostupnih = brojPrimeraka;
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

	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(int godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}

	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}

	public int getBrojDostupnih() {
		return brojDostupnih;
	}

	public void setBrojDostupnih(int brojDostupnih) {
		this.brojDostupnih = brojDostupnih;
	}

    
}
