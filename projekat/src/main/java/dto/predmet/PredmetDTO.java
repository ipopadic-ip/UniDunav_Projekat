package dto.predmet;

import java.util.ArrayList;

import dto.katedra.KatedraDTO;
import dto.korisnika.ProfesorDTO;
import dto.profesorPredmet.ProfesorPredmetDTO;

public class PredmetDTO {
	private Integer id;
    private String naziv;
	private int ests;
	private String informacijeOPredmetu;
	private ArrayList<ProfesorDTO> profesor = new ArrayList<ProfesorDTO>();
	private ProfesorPredmetDTO profesorPredmet;
	private KatedraDTO katedra;
	public PredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PredmetDTO(Integer id, String naziv, int ests, String informacijeOPredmetu, ArrayList<ProfesorDTO> profesor,
			ProfesorPredmetDTO profesorPredmet, KatedraDTO katedra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ests = ests;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.profesor = profesor;
		this.profesorPredmet = profesorPredmet;
		this.katedra = katedra;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public ArrayList<ProfesorDTO> getProfesor() {
		return profesor;
	}
	public void setProfesor(ArrayList<ProfesorDTO> profesor) {
		this.profesor = profesor;
	}
	public ProfesorPredmetDTO getProfesorPredmet() {
		return profesorPredmet;
	}
	public void setProfesorPredmet(ProfesorPredmetDTO profesorPredmet) {
		this.profesorPredmet = profesorPredmet;
	}
	public KatedraDTO getKatedra() {
		return katedra;
	}
	public void setKatedra(KatedraDTO katedra) {
		this.katedra = katedra;
	}
	
	
}
