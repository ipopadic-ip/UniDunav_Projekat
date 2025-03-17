package model.univerzitet;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import model.fakultet.Fakultet;

public class Univerzitet {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
    private String naziv;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String brojTelefona;
	
	private String opis;
	
	private ArrayList<Fakultet> fakulteti = new ArrayList<Fakultet>();

	public Univerzitet(Integer id, String naziv, String email, String brojTelefona, String opis,
			ArrayList<Fakultet> fakulteti) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.brojTelefona = brojTelefona;
		this.opis = opis;
		this.fakulteti = fakulteti;
	}

	public Univerzitet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Fakultet> getFakulteti() {
		return fakulteti;
	}

	public void setFakulteti(ArrayList<Fakultet> fakulteti) {
		this.fakulteti = fakulteti;
	}
	
	
}
