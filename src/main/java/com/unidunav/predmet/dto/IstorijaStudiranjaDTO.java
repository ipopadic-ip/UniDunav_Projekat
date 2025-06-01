package com.unidunav.predmet.dto;

public class IstorijaStudiranjaDTO {
    private String nazivPredmeta;
    private int brojPolaganja;
    private Integer ocena;
    private int brojECTS;

    public IstorijaStudiranjaDTO(String nazivPredmeta, int brojPolaganja, Integer ocena, int brojECTS) {
        this.nazivPredmeta = nazivPredmeta;
        this.brojPolaganja = brojPolaganja;
        this.ocena = ocena;
        this.brojECTS = brojECTS;
    }

    // Getteri i setteri
    public String getNazivPredmeta() { return nazivPredmeta; }
    public void setNazivPredmeta(String nazivPredmeta) { this.nazivPredmeta = nazivPredmeta; }

    public int getBrojPolaganja() { return brojPolaganja; }
    public void setBrojPolaganja(int brojPolaganja) { this.brojPolaganja = brojPolaganja; }

    public Integer getOcena() { return ocena; }
    public void setOcena(Integer ocena) { this.ocena = ocena; }

    public int getBrojECTS() { return brojECTS; }
    public void setBrojECTS(int brojECTS) { this.brojECTS = brojECTS; }
}
