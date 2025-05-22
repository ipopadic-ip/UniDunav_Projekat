package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class EvaluacijaZnanjaDTO {
	private Long id;
    private LocalDateTime vremePocetka;
    private int brojBodova;
    private Long tipEvaluacijeId;
    private Long pohadjanjeId;

    // Getteri i setteri
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getVremePocetka() { return vremePocetka; }

    public void setVremePocetka(LocalDateTime vremePocetka) { this.vremePocetka = vremePocetka; }

    public int getBrojBodova() { return brojBodova; }

    public void setBrojBodova(int brojBodova) { this.brojBodova = brojBodova; }

    public Long getTipEvaluacijeId() { return tipEvaluacijeId; }

    public void setTipEvaluacijeId(Long tipEvaluacijeId) { this.tipEvaluacijeId = tipEvaluacijeId; }

    public Long getPohadjanjeId() { return pohadjanjeId; }

    public void setPohadjanjeId(Long pohadjanjeId) { this.pohadjanjeId = pohadjanjeId; }
}
