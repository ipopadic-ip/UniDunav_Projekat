package model.korisnika;

import java.util.Date;

import jakarta.persistence.Column;
import model.adresa.Adresa;

public class Student extends RegistrovaniKorisnik{
	@Column(nullable=false)
	private Date godinaUpisa;
	@Column(nullable=false)
	private Adresa adresa;
	@Column(nullable=false)
	private String brojIndeksa;
	
	
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public Date getGodinaUpisa() {
		return godinaUpisa;
	}
	public void setGodinaUpisa(Date godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	public Student(Date godinaUpisa, Adresa adresa, String brojIndeksa) {
		super();
		this.godinaUpisa = godinaUpisa;
		this.adresa = adresa;
		this.brojIndeksa = brojIndeksa;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
