package com.unidunav.predmet.dto;

public class NastavniMaterijalDTO {
	private Long id;
    private String naziv;
    private String fajlPutanja;
    private Long predmetId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getFajlPutanja() {
		return fajlPutanja;
	}
	public void setFajlPutanja(String fajlPutanja) {
		this.fajlPutanja = fajlPutanja;
	}
	public Long getPredmetId() {
		return predmetId;
	}
	public void setPredmetId(Long predmetId) {
		this.predmetId = predmetId;
	}
    
}
