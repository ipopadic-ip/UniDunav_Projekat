package com.unidunav.profesorPredmet.dto;

import java.time.LocalDateTime;

public class TerminNastaveDTO {
    private Long id;
    private Long profesorPredmetId;
    private String ishod;
    private LocalDateTime terminPocetka;
    private LocalDateTime terminZavrsetka;
	public TerminNastaveDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TerminNastaveDTO(Long id, Long profesorPredmetId, String ishod, LocalDateTime terminPocetka,
			LocalDateTime terminZavrsetka) {
		super();
		this.id = id;
		this.profesorPredmetId = profesorPredmetId;
		this.ishod = ishod;
		this.terminPocetka = terminPocetka;
		this.terminZavrsetka = terminZavrsetka;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProfesorPredmetId() {
		return profesorPredmetId;
	}
	public void setProfesorPredmetId(Long profesorPredmetId) {
		this.profesorPredmetId = profesorPredmetId;
	}
	public String getIshod() {
		return ishod;
	}
	public void setIshod(String ishod) {
		this.ishod = ishod;
	}
	public LocalDateTime getTerminPocetka() {
		return terminPocetka;
	}
	public void setTerminPocetka(LocalDateTime terminPocetka) {
		this.terminPocetka = terminPocetka;
	}
	public LocalDateTime getTerminZavrsetka() {
		return terminZavrsetka;
	}
	public void setTerminZavrsetka(LocalDateTime terminZavrsetka) {
		this.terminZavrsetka = terminZavrsetka;
	}
	 
}