package com.unidunav.fakultet.dto;

public class FakultetSimpleDTO {
	private Long id;
    private String naziv;
    private String email;
    private String opis;
    private String lokacija;
    private String brojTelefona;

    private Long dekanId;
    private String dekanIme;
    private String dekanPrezime;
    private String dekanOpis;
    private String dekanSlika;
    private boolean deleted;
    
    
    
    
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Long getDekanId() {
		return dekanId;
	}
	public void setDekanId(Long dekanId) {
		this.dekanId = dekanId;
	}
	public String getDekanSlika() {
		return dekanSlika;
	}
	public void setDekanSlika(String dekanSlika) {
		this.dekanSlika = dekanSlika;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getLokacija() {
		return lokacija;
	}
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	public String getDekanIme() {
		return dekanIme;
	}
	public void setDekanIme(String dekanIme) {
		this.dekanIme = dekanIme;
	}
	public String getDekanPrezime() {
		return dekanPrezime;
	}
	public void setDekanPrezime(String dekanPrezime) {
		this.dekanPrezime = dekanPrezime;
	}
	public String getDekanOpis() {
		return dekanOpis;
	}
	public void setDekanOpis(String dekanOpis) {
		this.dekanOpis = dekanOpis;
	}
    
}
