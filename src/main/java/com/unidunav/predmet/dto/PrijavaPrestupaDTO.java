package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class PrijavaPrestupaDTO {
	private Long id;
    private String opis;
    private LocalDateTime datum;
    private Long pohadjanjeId;

    // Getteri i setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Long getPohadjanjeId() {
        return pohadjanjeId;
    }

    public void setPohadjanjeId(Long pohadjanjeId) {
        this.pohadjanjeId = pohadjanjeId;
    }
}
