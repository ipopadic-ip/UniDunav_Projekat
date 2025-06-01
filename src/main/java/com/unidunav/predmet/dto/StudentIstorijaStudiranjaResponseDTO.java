package com.unidunav.predmet.dto;

import java.util.List;

public class StudentIstorijaStudiranjaResponseDTO {
    private List<IstorijaStudiranjaDTO> predmeti;
    private Double prosecnaOcena;
    private int ukupnoECTS;

    public StudentIstorijaStudiranjaResponseDTO(List<IstorijaStudiranjaDTO> predmeti, Double prosecnaOcena, int ukupnoECTS) {
        this.predmeti = predmeti;
        this.prosecnaOcena = prosecnaOcena;
        this.ukupnoECTS = ukupnoECTS;
    }

    // Getteri i setteri
    public List<IstorijaStudiranjaDTO> getPredmeti() { return predmeti; }
    public void setPredmeti(List<IstorijaStudiranjaDTO> predmeti) { this.predmeti = predmeti; }

    public Double getProsecnaOcena() { return prosecnaOcena; }
    public void setProsecnaOcena(Double prosecnaOcena) { this.prosecnaOcena = prosecnaOcena; }

    public int getUkupnoECTS() { return ukupnoECTS; }
    public void setUkupnoECTS(int ukupnoECTS) { this.ukupnoECTS = ukupnoECTS; }
}