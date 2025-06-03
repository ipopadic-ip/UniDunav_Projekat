package com.unidunav.sluzbenik.dto;

import java.time.LocalDate;

public class PotvrdaDTO {

    private Long id;
    private String tip;
    private String tekst;
    private LocalDate datumIzdavanja;
    private Long studentId;  
    private String studentIme; // za prikaz u tabeli na frontu
    private String brojIndeksa;// samo ID studenta
    
	public String getStudentIme() {
		return studentIme;
	}
	public void setStudentIme(String studentIme) {
		this.studentIme = studentIme;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public PotvrdaDTO(Long id, String tip, String tekst, LocalDate datumIzdavanja, Long studentId, String studentIme,
			String brojIndeksa) {
		super();
		this.id = id;
		this.tip = tip;
		this.tekst = tekst;
		this.datumIzdavanja = datumIzdavanja;
		this.studentId = studentId;
		this.studentIme = studentIme;
		this.brojIndeksa = brojIndeksa;
	}
	public PotvrdaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
    
}
