package glavniPaket.model.korisnika;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "administratori")
public class Administrator  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private RegistrovaniKorisnik korisnik;


	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Administrator(Long id, RegistrovaniKorisnik korisnik) {
		super();
		this.id = id;
		this.korisnik = korisnik;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
	

}
