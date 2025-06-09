package com.unidunav.sluzbenik.dto;

import java.time.LocalDate;
public class IznajmljivanjeKnjigeDTO {
    private Long id;
    private Long primerakId;
    private String knjigaNaziv;
    private Long studentId;
    private String studentIndeks;
    private LocalDate datumIznajmljivanja;
    private LocalDate datumVracanja;
    
    
	public IznajmljivanjeKnjigeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IznajmljivanjeKnjigeDTO(Long id, Long primerakId, String knjigaNaziv, Long studentId, String studentIndeks,
			LocalDate datumIznajmljivanja, LocalDate datumVracanja) {
		super();
		this.id = id;
		this.primerakId = primerakId;
		this.knjigaNaziv = knjigaNaziv;
		this.studentId = studentId;
		this.studentIndeks = studentIndeks;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.datumVracanja = datumVracanja;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPrimerakId() {
		return primerakId;
	}
	public void setPrimerakId(Long primerakId) {
		this.primerakId = primerakId;
	}
	public String getKnjigaNaziv() {
		return knjigaNaziv;
	}
	public void setKnjigaNaziv(String knjigaNaziv) {
		this.knjigaNaziv = knjigaNaziv;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentIndeks() {
		return studentIndeks;
	}
	public void setStudentIndeks(String studentIndeks) {
		this.studentIndeks = studentIndeks;
	}
	public LocalDate getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}



   
}