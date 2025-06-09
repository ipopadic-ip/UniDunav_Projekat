package com.unidunav.predmet.model;

import java.util.ArrayList;
import java.util.List;

import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.obavestenje.model.Obavestenje;
import com.unidunav.profesorPredmet.model.ProfesorPredmet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private int ects;

    @Column(name = "informacije_o_predmetu")
    private String informacijeOPredmetu;
    
    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfesorPredmet> profesori;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();
    
//    @ManyToOne
//    @JoinColumn(name = "godinaStudija_id", nullable = false)
//    private GodinaStudija godinaStudija;
    @ManyToOne
    @JoinColumn(name = "godina_studija_id", nullable = false)
    private GodinaStudija godinaStudija;
    
    @OneToOne(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Silabus silabus;
    
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obavestenje> obavestenja = new ArrayList<>();
    
    

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public List<ProfesorPredmet> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<ProfesorPredmet> profesori) {
		this.profesori = profesori;
	}

	public List<PohadjanjePredmeta> getPohadjanja() {
		return pohadjanja;
	}

	public void setPohadjanja(List<PohadjanjePredmeta> pohadjanja) {
		this.pohadjanja = pohadjanja;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public Silabus getSilabus() {
		return silabus;
	}

	public void setSilabus(Silabus silabus) {
		this.silabus = silabus;
	}

	public List<Obavestenje> getObavestenja() {
		return obavestenja;
	}

	public void setObavestenja(List<Obavestenje> obavestenja) {
		this.obavestenja = obavestenja;
	}

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Predmet(Long id, String naziv, int ects, String informacijeOPredmetu, List<ProfesorPredmet> profesori,
			List<PohadjanjePredmeta> pohadjanja, GodinaStudija godinaStudija, Silabus silabus,
			List<Obavestenje> obavestenja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ects = ects;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.profesori = profesori;
		this.pohadjanja = pohadjanja;
		this.godinaStudija = godinaStudija;
		this.silabus = silabus;
		this.obavestenja = obavestenja;
	}
}
