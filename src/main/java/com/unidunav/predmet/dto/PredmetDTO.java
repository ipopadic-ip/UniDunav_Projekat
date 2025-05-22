package com.unidunav.predmet.dto;

public class PredmetDTO {

	private Long id;
    private String naziv;
    private int ects;
    private String informacijeOPredmetu;

    private Long godinaStudijaId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public int getEcts() { return ects; }
    public void setEcts(int ects) { this.ects = ects; }

    public String getInformacijeOPredmetu() { return informacijeOPredmetu; }
    public void setInformacijeOPredmetu(String informacijeOPredmetu) { this.informacijeOPredmetu = informacijeOPredmetu; }

    public Long getGodinaStudijaId() { return godinaStudijaId; }
    public void setGodinaStudijaId(Long godinaStudijaId) { this.godinaStudijaId = godinaStudijaId; }
}
