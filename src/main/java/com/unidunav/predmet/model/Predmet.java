package com.unidunav.predmet.model;

import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.katedra.model.Katedra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Predmet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private int ects;

    @Column(name = "informacije_o_predmetu")
    private String informacijeOPredmetu;
    
    @ManyToOne
    @JoinColumn(name = "godinaStudija_id", nullable = false)
    private GodinaStudija godinaStudija;

    
    @ManyToOne
    @JoinColumn(name = "katedra_id", nullable = false)
    private Katedra katedra;
    
    
	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Predmet(Long id, String naziv, int ects, String informacijeOPredmetu, GodinaStudija godinaStudija,
			Katedra katedra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ects = ects;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.godinaStudija = godinaStudija;
		this.katedra = katedra;
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

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getInformacijeOPredmetu() {
		return informacijeOPredmetu;
	}

	public void setInformacijeOPredmetu(String informacijeOPredmetu) {
		this.informacijeOPredmetu = informacijeOPredmetu;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}



	public Katedra getKatedra() {
		return katedra;
	}



	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}
	
	
}
