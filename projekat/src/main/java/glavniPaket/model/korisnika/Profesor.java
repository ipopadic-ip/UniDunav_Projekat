package glavniPaket.model.korisnika;


import jakarta.persistence.*;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.model.univerzitet.Univerzitet;

import java.util.Date;
import java.util.Set;

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private RegistrovaniKorisnik korisnik;

    @Column(nullable = false)
    private String titula;

    @Column(nullable = false)
    private String biografija;

    @OneToMany(mappedBy = "profesor")
    private Set<ProfesorPredmet> predmeti;

    @ManyToOne
    @JoinColumn(name = "univerzitet_id", nullable = true)
    private Univerzitet univerzitet;

    @ManyToOne
    @JoinColumn(name = "fakultet_id", nullable = true)
    private Fakultet fakultet;

    @ManyToOne
    @JoinColumn(name = "katedra_id", nullable = true)
    private Katedra katedra;
    
   
    public Profesor() {}

    public Profesor(RegistrovaniKorisnik korisnik, String titula, String biografija,
                    Set<ProfesorPredmet> predmeti, Univerzitet univerzitet,
                    Fakultet fakultet, Katedra katedra) {
        this.korisnik = korisnik;
        this.titula = titula;
        this.biografija = biografija;
        this.predmeti = predmeti;
        this.univerzitet = univerzitet;
        this.fakultet = fakultet;
        this.katedra = katedra;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}
   
}