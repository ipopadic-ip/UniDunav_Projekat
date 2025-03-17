package dto.univerzitet;

import java.util.ArrayList;

import dto.fakultet.FakultetDTO;

public class UniverzitetDTO {
	private Integer id;
    private String naziv;
	private String email;
	private String brojTelefona;
	private String opis;
	private ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();
	public UniverzitetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UniverzitetDTO(Integer id, String naziv, String email, String brojTelefona, String opis,
			ArrayList<FakultetDTO> fakulteti) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.brojTelefona = brojTelefona;
		this.opis = opis;
		this.fakulteti = fakulteti;
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
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public ArrayList<FakultetDTO> getFakulteti() {
		return fakulteti;
	}
	public void setFakulteti(ArrayList<FakultetDTO> fakulteti) {
		this.fakulteti = fakulteti;
	}
	
	
}
