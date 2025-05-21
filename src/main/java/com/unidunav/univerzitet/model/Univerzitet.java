package com.unidunav.univerzitet.model;

import java.util.List;

import com.unidunav.fakultet.model.Fakultet;
import com.unidunav.profesor.model.Profesor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Univerzitet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String naziv;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String brojTelefona;

    private String opis;
    
    private String slikaPath;

    @OneToMany(mappedBy = "univerzitet")
    private List<Fakultet> fakulteti;

    @OneToOne
    @JoinColumn(name = "rektor_id", nullable = true)
    private Profesor rektor;

	public Univerzitet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Univerzitet(Long id, String naziv, String email, String brojTelefona, String opis,
			List<Fakultet> fakulteti, Profesor rektor) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.brojTelefona = brojTelefona;
		this.opis = opis;
		this.fakulteti = fakulteti;
		this.rektor = rektor;
	}
	
	public String getSlikaPath() {
	    return slikaPath;
	}

	public void setSlikaPath(String slikaPath) {
	    this.slikaPath = slikaPath;
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

	public List<Fakultet> getFakulteti() {
		return fakulteti;
	}

	public void setFakulteti(List<Fakultet> list) {
		this.fakulteti = list;
	}

	public Profesor getRektor() {
		return rektor;
	}

	public void setRektor(Profesor rektor) {
		this.rektor = rektor;
	}

}
