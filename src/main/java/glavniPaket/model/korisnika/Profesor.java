package glavniPaket.model.korisnika;


import jakarta.persistence.*;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.departman.Departman;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.model.univerzitet.Univerzitet;

import java.util.Date;
import java.util.Set;


@Entity
public class Profesor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private RegistrovaniKorisnik korisnik;

    @Column(nullable = false)
    private String titula;

    @Column(nullable = false)
    private String biografija;

    @OneToMany(mappedBy = "profesor")
    private Set<ProfesorPredmet> predmeti;
	@OneToOne(mappedBy = "rektor")
	private Univerzitet univerzitet;
	
	@OneToOne(mappedBy = "dekan")
	private Fakultet fakultet;
	
	@OneToOne(mappedBy = "sefDepartmana")
	private Departman departman;
	
	@OneToOne(mappedBy = "sefKatedre")
	private Katedra katedra;
	
	@OneToOne(mappedBy = "rukovodilac")
	private StudijskiProgram studijskiProgram;
   
    public Profesor() {}

	public Profesor(Long id, RegistrovaniKorisnik korisnik, String titula, String biografija,
			Set<ProfesorPredmet> predmeti, Univerzitet univerzitet, Fakultet fakultet, Departman departman,
			Katedra katedra, StudijskiProgram studijskiProgram) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.titula = titula;
		this.biografija = biografija;
		this.predmeti = predmeti;
		this.univerzitet = univerzitet;
		this.fakultet = fakultet;
		this.departman = departman;
		this.katedra = katedra;
		this.studijskiProgram = studijskiProgram;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	public Set<ProfesorPredmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<ProfesorPredmet> predmeti) {
		this.predmeti = predmeti;
	}

	public Univerzitet getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(Univerzitet univerzitet) {
		this.univerzitet = univerzitet;
	}

	public Fakultet getFakultet() {
		return fakultet;
	}

	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}

	public Departman getDepartman() {
		return departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}

	public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

	public StudijskiProgram getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}

    }