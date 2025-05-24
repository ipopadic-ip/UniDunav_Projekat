package com.unidunav.fakultet.model;

import java.util.ArrayList;
import java.util.List;

import com.unidunav.dapartman.model.Departman;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.univerzitet.model.Univerzitet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity 
public class Fakultet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
    private String naziv;
	
	@Column(nullable=false)
	private String email;
	
	private String lokacija;
	
	private String brojTelefona;
	
	@ManyToOne 
    @JoinColumn(name = "univerzitet_id", nullable = false)
    private Univerzitet univerzitet;

	@OneToOne
	@JoinColumn(name = "dekan_id", nullable = true)
	private Profesor dekan;
	
	
	@OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = true)
//	private ArrayList<Departman> departmani = new ArrayList<Departman>();
	private List<Departman> departmani = new ArrayList<>();

	
	private String opis;

	public Fakultet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

public Fakultet(Long id, String naziv, String email, String lokacija, String brojTelefona, Univerzitet univerzitet,
			Profesor dekan, List<Departman> departmani, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.lokacija = lokacija;
		this.brojTelefona = brojTelefona;
		this.univerzitet = univerzitet;
		this.dekan = dekan;
		this.departmani = departmani;
		this.opis = opis;
	}



public String getBrojTelefona() {
		return brojTelefona;
	}



	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}



public String getLokacija() {
		return lokacija;
	}



	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}



//public Fakultet(Long id, String naziv, String email, String lokacija, Univerzitet univerzitet, Profesor dekan,
//			List<Departman> departmani, String opis) {
//		super();
//		this.id = id;
//		this.naziv = naziv;
//		this.email = email;
//		this.lokacija = lokacija;
//		this.univerzitet = univerzitet;
//		this.dekan = dekan;
//		this.departmani = departmani;
//		this.opis = opis;
//	}



//public Fakultet(Long id, String naziv, String email, Univerzitet univerzitet, Profesor dekan,
//			List<Departman> departmani, String opis) {
//		super();
//		this.id = id;
//		this.naziv = naziv;
//		this.email = email;
//		this.univerzitet = univerzitet;
//		this.dekan = dekan;
//		this.departmani = departmani;
//		this.opis = opis;
//	}



//	public Fakultet(Long id, String naziv, String email, Univerzitet univerzitet, Profesor dekan,
//			ArrayList<Departman> departmani, String opis) {
//		super();
//		this.id = id;
//		this.naziv = naziv;
//		this.email = email;
//		this.univerzitet = univerzitet;
//		this.dekan = dekan;
//		this.departmani = departmani;
//		this.opis = opis;
//	}



	public Long getId() {
		return id;
	}

	public List<Departman> getDepartmani() {
		return departmani;
	}



	public void setDepartmani(List<Departman> departmani) {
		this.departmani = departmani;
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

	public Univerzitet getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(Univerzitet univerzitet) {
		this.univerzitet = univerzitet;
	}

	public Profesor getDekan() {
		return dekan;
	}

	public void setDekan(Profesor dekan) {
		this.dekan = dekan;
	}

//	public ArrayList<Departman> getDepartmani() {
//		return departmani;
//	}

//	public void setDepartmani(ArrayList<Departman> departmani) {
//		this.departmani = departmani;
//	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

}
