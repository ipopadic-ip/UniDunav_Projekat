package glavniPaket.model.korisnika;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;

@Entity
@Table(name = "studenti")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private RegistrovaniKorisnik korisnik;

    @Column(nullable = false)
    private Date godinaUpisa;

    @ManyToOne
    @JoinColumn(name = "adresa_id", nullable = false)
    private Adresa adresa;

    @Column(nullable = false, unique = true)
    private String brojIndeksa;

    public Student() {}

    public Student(RegistrovaniKorisnik korisnik, Date godinaUpisa, Adresa adresa, String brojIndeksa) {
        this.korisnik = korisnik;
        this.godinaUpisa = godinaUpisa;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Date getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(Date godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

 
}
