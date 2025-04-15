package glavniPaket.dto.katedra;

import java.util.ArrayList;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;

public class KatedraDTO {
	private Long id;
    private String naziv;
	private ArrayList<PredmetDTO> predmeti = new ArrayList<PredmetDTO>();
	private String opis;
	private FakultetDTO fakultet;
	private ArrayList<TipStudijaDTO> tipoviStudija = new ArrayList<TipStudijaDTO>();
	private ProfesorDTO sefKatedre;
	
	public KatedraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KatedraDTO(Long id, String naziv, ArrayList<PredmetDTO> predmeti, String opis, FakultetDTO fakultet,  ArrayList<TipStudijaDTO> tipoviStudija, ProfesorDTO sefKatedre) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.predmeti = predmeti;
		this.opis = opis;
		this.fakultet = fakultet;
		this.tipoviStudija = tipoviStudija;
		this.sefKatedre = sefKatedre;
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
	public ArrayList<TipStudijaDTO> getTipoviStudija() {
		return tipoviStudija;
	}
	public void setTipoviStudija(ArrayList<TipStudijaDTO> tipoviStudija) {
		this.tipoviStudija = tipoviStudija;
	}
	public ProfesorDTO getSefKatedre() {
		return sefKatedre;
	}
	public void setSefKatedre(ProfesorDTO sefKatedre) {
		this.sefKatedre = sefKatedre;
	}
	
	
}
