package model.korisnika;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import model.adresa.*;

@Entity
public class RegistrovaniKorisnik {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String ime;
	@Column(nullable=false)
	private String prezime;
	@Column(nullable=false)
	private String korisnickoIme;
	@Column(nullable=false)
	private Date datumRodjenja;
	@Column(nullable=false)
	private Mesto mestoRodjenja;
	@Column(nullable=false)
	private String jmbg;
	@Column(nullable=false)
	private String lozinka;
	@Column(nullable=false)
	private String email;
	public RegistrovaniKorisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, Date datumRodjenja,
			Mesto mestoRodjenja, String jmbg, String lozinka, String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.datumRodjenja = datumRodjenja;
		this.mestoRodjenja = mestoRodjenja;
		this.jmbg = jmbg;
		this.lozinka = lozinka;
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Mesto getMestoRodjenja() {
		return mestoRodjenja;
	}
	public void setMestoRodjenja(Mesto mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
