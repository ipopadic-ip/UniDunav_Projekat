package com.unidunav.user.dto.request;

public class CreateStudentRequest {
	private String email;
    private String password;
    private String ime;
    private String prezime;
    private String brojIndeksa;
    private int godinaUpisa;
    private double prosecnaOcena;
    private int ukupnoEcts;
    
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public int getUkupnoEcts() {
		return ukupnoEcts;
	}
	public void setUkupnoEcts(int ukupnoEcts) {
		this.ukupnoEcts = ukupnoEcts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
    
}
