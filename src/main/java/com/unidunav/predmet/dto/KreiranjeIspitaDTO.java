package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class KreiranjeIspitaDTO {
    private Long predmetId;
    private LocalDateTime datumIspita;
	public KreiranjeIspitaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KreiranjeIspitaDTO(Long predmetId, LocalDateTime datumIspita) {
		super();
		this.predmetId = predmetId;
		this.datumIspita = datumIspita;
	}
	public Long getPredmetId() {
		return predmetId;
	}
	public void setPredmetId(Long predmetId) {
		this.predmetId = predmetId;
	}
	public LocalDateTime getDatumIspita() {
		return datumIspita;
	}
	public void setDatumIspita(LocalDateTime datumIspita) {
		this.datumIspita = datumIspita;
	}

    
    
}