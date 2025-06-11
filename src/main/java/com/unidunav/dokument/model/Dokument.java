package com.unidunav.dokument.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Dokument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String opis;


    @Column(nullable = false)
	private boolean deleted = false;

    @OneToMany(mappedBy = "dokument", cascade = CascadeType.ALL)
    private List<VerzijaDokumenta> verzije = new ArrayList<>();

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


	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<VerzijaDokumenta> getVerzije() {
		return verzije;
	}

	public void setVerzije(List<VerzijaDokumenta> verzije) {
		this.verzije = verzije;
	}

	
	

	public Dokument(Long id, String naziv, String opis, boolean deleted,
			List<VerzijaDokumenta> verzije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.deleted = deleted;
		this.verzije = verzije;
	}

	public Dokument() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
