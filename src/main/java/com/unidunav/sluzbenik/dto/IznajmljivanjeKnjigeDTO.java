package com.unidunav.sluzbenik.dto;

import java.time.LocalDate;

public class IznajmljivanjeKnjigeDTO {

    private Long knjiga;
    private Long korisnik;
    private int brojPrimeraka;
    private LocalDate datum;
    private boolean vraceno;


    public boolean isVraceno() {
		return vraceno;
	}

	public void setVraceno(boolean vraceno) {
		this.vraceno = vraceno;
	}

	public Long getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Long knjiga) {
        this.knjiga = knjiga;
    }

    public Long getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Long korisnik) {
        this.korisnik = korisnik;
    }

    public int getBrojPrimeraka() {
        return brojPrimeraka;
    }

    public void setBrojPrimeraka(int brojPrimeraka) {
        this.brojPrimeraka = brojPrimeraka;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
