package com.unidunav.administrator.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "profesor")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfesorXmlDTO {

    @XmlElement
    private String ime;

    @XmlElement
    private String prezime;

    @XmlElement
    private String email;

    @XmlElement
    private String biografija;

    public ProfesorXmlDTO() {}

    public ProfesorXmlDTO(String ime, String prezime, String email, String biografija) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.biografija = biografija;
    }

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

    
}
