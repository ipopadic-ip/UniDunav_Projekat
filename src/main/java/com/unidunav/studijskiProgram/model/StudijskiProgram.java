package com.unidunav.studijskiProgram.model;

import java.util.ArrayList;
import java.util.List;

import com.unidunav.godinaStudija.model.GodinaStudija;
import com.unidunav.katedra.model.Katedra;
import com.unidunav.profesor.model.Profesor;
import com.unidunav.tipStudija.model.TipStudija;

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
public class StudijskiProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    
    @Column(nullable = false)
    private boolean deleted = false;

	@Column(length=3000)
    private String opis;

    @OneToMany(mappedBy = "studijskiProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GodinaStudija> godineStudija = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipStudija_id")
    private TipStudija tipStudija;
    
    @Column(nullable = false)
    private String tipStudijaUri;

    @OneToOne
    @JoinColumn(name = "rukovodilac_id", nullable = true)
    private Profesor rukovodilac;
    
    @ManyToOne
    @JoinColumn(name = "katedra_id")
    private Katedra katedra;


    public StudijskiProgram() {}
    
    

//    public StudijskiProgram(Long id, String naziv, String opis, List<GodinaStudija> godineStudija,
//                            TipStudija tipStudija, Profesor rukovodilac) {
//        this.id = id;
//        this.naziv = naziv;
//        this.opis = opis;
//        this.godineStudija = godineStudija;
//        this.tipStudija = tipStudija;
//        this.rukovodilac = rukovodilac;
//    }

    // === Getteri i setteri ===
    

    public String getTipStudijaUri() {
		return tipStudijaUri;
	}



	public void setTipStudijaUri(String tipStudijaUri) {
		this.tipStudijaUri = tipStudijaUri;
	}



	public StudijskiProgram(Long id, String naziv, String opis, List<GodinaStudija> godineStudija,
			TipStudija tipStudija, Profesor rukovodilac, Katedra katedra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.godineStudija = godineStudija;
		this.tipStudija = tipStudija;
		this.rukovodilac = rukovodilac;
		this.katedra = katedra;
	}
    
    public boolean isDeleted() {
  		return deleted;
  	}



  	public void setDeleted(boolean deleted) {
  		this.deleted = deleted;
  	}



	public Long getId() {
        return id;
    }

    public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
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

    public List<GodinaStudija> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(List<GodinaStudija> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public TipStudija getTipStudija() {
        return tipStudija;
    }

    public void setTipStudija(TipStudija tipStudija) {
        this.tipStudija = tipStudija;
    }

    public Profesor getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(Profesor rukovodilac) {
        this.rukovodilac = rukovodilac;
    }
}
