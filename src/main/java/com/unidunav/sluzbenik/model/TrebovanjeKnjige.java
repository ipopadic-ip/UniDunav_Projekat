package com.unidunav.sluzbenik.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrebovanjeKnjige {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String autor;
    private String zanr;
    private int godinaIzdavanja;
    private String izdavac;
    private int brojPrimeraka;

    private LocalDate datumTrebovanja;

    private String status;

	public TrebovanjeKnjige() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrebovanjeKnjige(Long id, String naziv, String autor, String zanr, int godinaIzdavanja, String izdavac,
			int brojPrimeraka, LocalDate datumTrebovanja, String status) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.autor = autor;
		this.zanr = zanr;
		this.godinaIzdavanja = godinaIzdavanja;
		this.izdavac = izdavac;
		this.brojPrimeraka = brojPrimeraka;
		this.datumTrebovanja = datumTrebovanja;
		this.status = status;
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

	public LocalDate getDatumTrebovanja() {
		return datumTrebovanja;
	}

	public void setDatumTrebovanja(LocalDate datumTrebovanja) {
		this.datumTrebovanja = datumTrebovanja;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}