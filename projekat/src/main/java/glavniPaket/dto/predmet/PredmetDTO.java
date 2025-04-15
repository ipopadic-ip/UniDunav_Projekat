package glavniPaket.dto.predmet;

import java.util.ArrayList;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.profesorPredmet.ProfesorPredmetDTO;

public class PredmetDTO {
	private Long id;
    private String naziv;
	private int ests;
	private String informacijeOPredmetu;
	private ArrayList<ProfesorDTO> profesor = new ArrayList<ProfesorDTO>();
	private ProfesorPredmetDTO profesorPredmet;
	private KatedraDTO katedra;
	private GodinaStudijaDTO godinaStudija;
	public PredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PredmetDTO(Long id, String naziv, int ests, String informacijeOPredmetu, ArrayList<ProfesorDTO> profesor,
			ProfesorPredmetDTO profesorPredmet, KatedraDTO katedra, GodinaStudijaDTO godinaStudija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ests = ests;
		this.informacijeOPredmetu = informacijeOPredmetu;
		this.profesor = profesor;
		this.profesorPredmet = profesorPredmet;
		this.katedra = katedra;
		this.godinaStudija = godinaStudija;
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

	public GodinaStudijaDTO getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	
	
}
