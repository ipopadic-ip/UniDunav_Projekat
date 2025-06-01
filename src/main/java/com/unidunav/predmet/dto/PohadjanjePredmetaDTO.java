package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class PohadjanjePredmetaDTO {
	private Long id;
    private Integer ocena;
    private int brojPolaganja;
    private boolean aktivan;
    private LocalDateTime datumPocetka;
    private LocalDateTime datumZavrsetka;
    private Long studentId;
    private Long predmetId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOcena() {
		return ocena;
	}
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	public int getBrojPolaganja() {
		return brojPolaganja;
	}
	public void setBrojPolaganja(int brojPolaganja) {
		this.brojPolaganja = brojPolaganja;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public LocalDateTime getDatumZavrsetka() {
		return datumZavrsetka;
	}
	public void setDatumZavrsetka(LocalDateTime datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getPredmetId() {
		return predmetId;
	}
	public void setPredmetId(Long predmetId) {
		this.predmetId = predmetId;
	}
    
    

}
