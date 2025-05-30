package com.unidunav.obavestenje.dto;

import java.time.LocalDate;

public class ObavestenjeDTO {
    private String tekst;
    private LocalDate datum;
    private Long predmetId;
//    private Long autorId;

    public String getTekst() { return tekst; }
    public void setTekst(String tekst) { this.tekst = tekst; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

//    public Long getAutorId() { return autorId; }
//    public void setAutorId(Long autorId) { this.autorId = autorId; }
}
