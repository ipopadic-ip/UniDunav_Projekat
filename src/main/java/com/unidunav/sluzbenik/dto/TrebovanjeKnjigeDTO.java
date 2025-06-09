package com.unidunav.sluzbenik.dto;

public class TrebovanjeKnjigeDTO {

    private String naziv;
    private String autor;
    private String zanr;
    private int godinaIzdavanja;
    private String izdavac;
    private int brojPrimeraka;
	public TrebovanjeKnjigeDTO(String naziv, String autor, String zanr, int godinaIzdavanja, String izdavac,
			int brojPrimeraka) {
		super();
		this.naziv = naziv;
		this.autor = autor;
		this.zanr = zanr;
		this.godinaIzdavanja = godinaIzdavanja;
		this.izdavac = izdavac;
		this.brojPrimeraka = brojPrimeraka;
	}
	public TrebovanjeKnjigeDTO() {
		super();
		// TODO Auto-generated constructor stub
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