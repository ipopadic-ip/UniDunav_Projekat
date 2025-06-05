package com.unidunav.sluzbenik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PrimerakKnjige {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private boolean dostupan;

    @ManyToOne
    @JoinColumn(name = "knjiga_id")
    private Knjiga knjiga;

	public PrimerakKnjige(Long id, String isbn, boolean dostupan, Knjiga knjiga) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.dostupan = dostupan;
		this.knjiga = knjiga;
	}

	public PrimerakKnjige() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isDostupan() {
		return dostupan;
	}

	public void setDostupan(boolean dostupan) {
		this.dostupan = dostupan;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
    
    
}