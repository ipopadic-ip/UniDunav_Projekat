package com.unidunav.sluzbenik.dto;

import java.time.LocalDate;

public class TrebovanjeDTO {
    private Long id;
    private String nazivStavke;
    private int kolicina;
    private LocalDate datum;

    public TrebovanjeDTO() {}

    public TrebovanjeDTO(Long id, String nazivStavke, int kolicina, LocalDate datum) {
        this.id = id;
        this.nazivStavke = nazivStavke;
        this.kolicina = kolicina;
        this.datum = datum;
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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
    
}
