package model.predmet;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import model.katedra.Katedra;
import model.korisnika.Profesor;
import model.profesorPredmet.ProfesorPredmet;

public class Predmet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
    private String naziv;
	
	@Column(nullable=false)
	private int ests;
	
	private String informacijeOPredmetu;
	
	@Column(nullable=false)
	private ArrayList<Profesor> profesor = new ArrayList<Profesor>();
	
//	@ManyToMany(mappedBy = Predmet)
	private ProfesorPredmet profesorPredmet;
	
	@ManyToOne
	@JoinColumn(name = "katedra_id")
	private Katedra katedra;

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Predmet(Long id, String naziv, int ests, String informacijeOPredmetu, ArrayList<Profesor> profesor,
			ProfesorPredmet profesorPredmet, Katedra katedra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ests = ests;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.profesor = profesor;
		this.profesorPredmet = profesorPredmet;
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

	public ArrayList<Profesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(ArrayList<Profesor> profesor) {
		this.profesor = profesor;
	}

	public ProfesorPredmet getProfesorPredmet() {
		return profesorPredmet;
	}

	public void setProfesorPredmet(ProfesorPredmet profesorPredmet) {
		this.profesorPredmet = profesorPredmet;
	}

	public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

	
	
	
}
