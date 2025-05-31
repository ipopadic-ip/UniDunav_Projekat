package com.unidunav.obavestenje.dto;



import java.time.LocalDate;

public class ObavestenjeStudentuDTO {
    private String predmetNaziv;
    private String tekst;
    private LocalDate datum;

    public ObavestenjeStudentuDTO(String predmetNaziv, String tekst, LocalDate datum) {
        this.predmetNaziv = predmetNaziv;
        this.tekst = tekst;
        this.datum = datum;
    }

    public String getPredmetNaziv() {
        return predmetNaziv;
    }

    public String getTekst() {
        return tekst;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
