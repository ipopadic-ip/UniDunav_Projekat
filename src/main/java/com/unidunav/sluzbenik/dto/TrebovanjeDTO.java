package com.unidunav.sluzbenik.dto;

import java.time.LocalDate;

public class TrebovanjeDTO {
    private Long id;
    private String nazivStavke;
    private int kolicina;
    private LocalDate datumTrebovanja;
    private Long sluzbenikId;
    private String sluzbenikIme;
    private String status;


    public String getSluzbenikIme() {
		return sluzbenikIme;
	}

	public void setSluzbenikIme(String sluzbenikIme) {
		this.sluzbenikIme = sluzbenikIme;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TrebovanjeDTO(Long id, String nazivStavke, int kolicina, LocalDate datumTrebovanja, Long sluzbenikId,
			String sluzbenikIme, String status) {
		super();
		this.id = id;
		this.nazivStavke = nazivStavke;
		this.kolicina = kolicina;
		this.datumTrebovanja = datumTrebovanja;
		this.sluzbenikId = sluzbenikId;
		this.sluzbenikIme = sluzbenikIme;
		this.status = status;
	}

	public TrebovanjeDTO() {}

   

	public Long getSluzbenikId() {
		return sluzbenikId;
	}

	public void setSluzbenikId(Long sluzbenikId) {
		this.sluzbenikId = sluzbenikId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivStavke() {
		return nazivStavke;
	}

	public void setNazivStavke(String nazivStavke) {
		this.nazivStavke = nazivStavke;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public LocalDate getDatumTrebovanja() {
	    return datumTrebovanja;
	}

	public void setDatumTrebovanja(LocalDate datumTrebovanja) {
	    this.datumTrebovanja = datumTrebovanja;
	}
    
}