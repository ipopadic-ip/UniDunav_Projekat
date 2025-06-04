package com.unidunav.obavestenje.dto;

import java.time.LocalDate;

import com.unidunav.obavestenje.model.OpsteObavestenje;

public class OpsteObavestenjeResponseDTO {
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private String autorIme;

    public OpsteObavestenjeResponseDTO(OpsteObavestenje o) {
        this.id = o.getId();
        this.naslov = o.getNaslov();
        this.tekst = o.getTekst();
        this.datum = o.getDatum();
        this.autorIme = o.getAutor().getIme() + " " + o.getAutor().getPrezime(); // prilagodi ako nema prezime
    }

    public Long getId() { return id; }
    public String getNaslov() { return naslov; }
    public String getTekst() { return tekst; }
    public LocalDate getDatum() { return datum; }
    public String getAutorIme() { return autorIme; }
}