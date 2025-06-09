package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class EvaluacijaZnanjaCreateDTO {
    private Long predmetId;
    private Long tipEvaluacijeId;
    private LocalDateTime vremePocetka;
	public EvaluacijaZnanjaCreateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaluacijaZnanjaCreateDTO(Long predmetId, Long tipEvaluacijeId, LocalDateTime vremePocetka) {
		super();
		this.predmetId = predmetId;
		this.tipEvaluacijeId = tipEvaluacijeId;
		this.vremePocetka = vremePocetka;
	}
	public Long getPredmetId() {
		return predmetId;
	}
	public void setPredmetId(Long predmetId) {
		this.predmetId = predmetId;
	}
	public Long getTipEvaluacijeId() {
		return tipEvaluacijeId;
	}
	public void setTipEvaluacijeId(Long tipEvaluacijeId) {
		this.tipEvaluacijeId = tipEvaluacijeId;
	}
	public LocalDateTime getVremePocetka() {
		return vremePocetka;
	}
	public void setVremePocetka(LocalDateTime vremePocetka) {
		this.vremePocetka = vremePocetka;
	}
    
    
}