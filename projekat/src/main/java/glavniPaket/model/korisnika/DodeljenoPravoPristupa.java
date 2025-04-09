package glavniPaket.model.korisnika;
import jakarta.persistence.*;

@Entity
public class DodeljenoPravoPristupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private RegistrovaniKorisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "pravo_pristupa_id", nullable = false)
    private PravoPristupa pravoPristupa;

    public DodeljenoPravoPristupa() {}

    public DodeljenoPravoPristupa(RegistrovaniKorisnik korisnik, PravoPristupa pravoPristupa) {
        this.korisnik = korisnik;
        this.pravoPristupa = pravoPristupa;
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

	public PravoPristupa getPravoPristupa() {
		return pravoPristupa;
	}

	public void setPravoPristupa(PravoPristupa pravoPristupa) {
		this.pravoPristupa = pravoPristupa;
	}
    

}
