package com.unidunav.administrator.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentXmlDTO {

    @XmlElement
    private String ime;

    @XmlElement
    private String prezime;

    @XmlElement
    private String brojIndeksa;

    @XmlElement
    private int godinaUpisa;

    @XmlElement
    private double prosecnaOcena;
    
    @XmlElement
    private int ukupnoEcts;

    public StudentXmlDTO() {}
    
    

public StudentXmlDTO(String ime, String prezime, String brojIndeksa, int godinaUpisa, double prosecnaOcena,
			int ukupnoEcts) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brojIndeksa = brojIndeksa;
		this.godinaUpisa = godinaUpisa;
		this.prosecnaOcena = prosecnaOcena;
		this.ukupnoEcts = ukupnoEcts;
	}



//    public StudentXmlDTO(String ime, String prezime, String brojIndeksa, int godinaUpisa, double prosecnaOcena) {
//        this.ime = ime;
//        this.prezime = prezime;
//        this.brojIndeksa = brojIndeksa;
//        this.godinaUpisa = godinaUpisa;
//        this.prosecnaOcena = prosecnaOcena;
//    }
    

	public int getUkupnoEcts() {
		return ukupnoEcts;
	}

	public void setUkupnoEcts(int ukupnoEcts) {
		this.ukupnoEcts = ukupnoEcts;
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

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

    
}

