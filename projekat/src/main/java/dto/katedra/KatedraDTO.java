package dto.katedra;

import java.util.ArrayList;

import dto.fakultet.FakultetDTO;
import dto.predmet.PredmetDTO;

public class KatedraDTO {
	private Integer id;
    private String naziv;
	private ArrayList<PredmetDTO> predmeti = new ArrayList<PredmetDTO>();
	private String opis;
	private FakultetDTO fakultet;
	public KatedraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KatedraDTO(Integer id, String naziv, ArrayList<PredmetDTO> predmeti, String opis, FakultetDTO fakultet) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.predmeti = predmeti;
		this.opis = opis;
		this.fakultet = fakultet;
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
	public ArrayList<PredmetDTO> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(ArrayList<PredmetDTO> predmeti) {
		this.predmeti = predmeti;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public FakultetDTO getFakultet() {
		return fakultet;
	}
	public void setFakultet(FakultetDTO fakultet) {
		this.fakultet = fakultet;
	}
	
	
}
