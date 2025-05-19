package glavniPaket.model.predmet;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;

@Entity
@Table(name = "predmet")
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

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfesorPredmet> profesori;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "godinaStudija_id", nullable = false)
    private GodinaStudija godinaStudija;
    
    @OneToOne(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Silabus silabus;
    
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obavestenje> obavestenja = new ArrayList<>();

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