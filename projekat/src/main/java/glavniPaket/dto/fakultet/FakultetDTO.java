package glavniPaket.dto.fakultet;

import java.util.ArrayList;

import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.katedra.Katedra;

public class FakultetDTO {
	private Integer id;
    private String naziv;
	private String email;
	private UniverzitetDTO univerzitet;
	private ArrayList<Katedra> katedre = new ArrayList<Katedra>();
	private String opis;
	public FakultetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FakultetDTO(Integer id, String naziv, String email, UniverzitetDTO univerzitet, ArrayList<Katedra> katedre,
			String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.univerzitet = univerzitet;
		this.katedre = katedre;
		this.opis = opis;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UniverzitetDTO getUniverzitet() {
		return univerzitet;
	}
	public void setUniverzitet(UniverzitetDTO univerzitet) {
		this.univerzitet = univerzitet;
	}
	public ArrayList<Katedra> getKatedre() {
		return katedre;
	}
	public void setKatedre(ArrayList<Katedra> katedre) {
		this.katedre = katedre;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
}
