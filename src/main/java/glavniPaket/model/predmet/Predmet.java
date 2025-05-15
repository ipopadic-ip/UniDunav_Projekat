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
    private int ests;

    @Column(name = "informacije_o_predmetu")
    private String informacijeOPredmetu;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfesorPredmet> profesori;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "godinaStudija_id", nullable = false)
    private GodinaStudija godinaStudija;
    
    @ManyToOne
    @JoinColumn(name = "katedra_id", nullable = true)
    private Katedra katedra;

    

    public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

public Predmet(Long id, String naziv, int ests, String informacijeOPredmetu, List<ProfesorPredmet> profesori,
			List<PohadjanjePredmeta> pohadjanja, GodinaStudija godinaStudija, Katedra katedra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ests = ests;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.profesori = profesori;
		this.pohadjanja = pohadjanja;
		this.godinaStudija = godinaStudija;
		this.katedra = katedra;
	}

//	public Predmet(Long id, String naziv, int ests, String informacijeOPredmetu, List<ProfesorPredmet> profesori,
//			List<PohadjanjePredmeta> pohadjanja, GodinaStudija godinaStudija) {
//		super();
//		this.id = id;
//		this.naziv = naziv;
//		this.ests = ests;
//		this.informacijeOPredmetu = informacijeOPredmetu;
//		this.profesori = profesori;
//		this.pohadjanja = pohadjanja;
//		this.godinaStudija = godinaStudija;
//	}

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getEsts() {
		return ests;
	}

	public void setEsts(int ests) {
		this.ests = ests;
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

}