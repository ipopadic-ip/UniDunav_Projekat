package com.unidunav.obavestenje.dto;
import java.time.LocalDate;

import com.unidunav.user.model.User;

public class OpsteObavestenjeDTO {

    private Long id;
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private User autor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public User getAutor() {
		return autor;
	}
	public void setAutor(User autor) {
		this.autor = autor;
	}

    
}
