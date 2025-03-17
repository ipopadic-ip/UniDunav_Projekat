package model.korisnika;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import model.adresa.Mesto;
import model.fakultet.Fakultet;
import model.katedra.Katedra;
import model.profesorPredmet.ProfesorPredmet;
import model.univerzitet.Univerzitet;

@Entity
public class Profesor extends RegistrovaniKorisnik {

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

    @OneToOne(mappedBy = "sefKatedre")
    private Katedra katedra;

    public Profesor() {
        super();
    }

    public Profesor(String titula) {
        this.titula = titula;
    }

	public Profesor(Integer id, String ime, String prezime, String korisnickoIme, Date datumRodjenja,
			Mesto mestoRodjenja, String jmbg, String lozinka, String email) {
		super(id, ime, prezime, korisnickoIme, datumRodjenja, mestoRodjenja, jmbg, lozinka, email);
		// TODO Auto-generated constructor stub
	}

	public Profesor(String titula, String biografija, Set<ProfesorPredmet> predmeti, Univerzitet univerzitet,
			Fakultet fakultet, Katedra katedra) {
		super();
		this.titula = titula;
		this.biografija = biografija;
		this.predmeti = predmeti;
		this.univerzitet = univerzitet;
		this.fakultet = fakultet;
		this.katedra = katedra;
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
