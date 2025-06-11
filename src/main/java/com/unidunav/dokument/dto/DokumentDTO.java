package com.unidunav.dokument.dto;

public class DokumentDTO {
    private Long id;
    private String naziv;
    private String opis;
    private boolean deleted;
    
    
    
	public DokumentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DokumentDTO(Long id, String naziv, String opis, boolean deleted) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.deleted = deleted;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
    
}

