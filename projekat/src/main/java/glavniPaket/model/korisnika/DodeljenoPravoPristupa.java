package glavniPaket.model.korisnika;
import jakarta.persistence.*;

@Entity
public class DodeljenoPravoPristupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private RegistrovaniKorisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "pravo_pristupa_id", nullable = false)
    private PravoPristupa pravoPristupa;

	public DodeljenoPravoPristupa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DodeljenoPravoPristupa(Long id, RegistrovaniKorisnik korisnik, PravoPristupa pravoPristupa) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.pravoPristupa = pravoPristupa;
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

	public PravoPristupa getPravoPristupa() {
		return pravoPristupa;
	}

	public void setPravoPristupa(PravoPristupa pravoPristupa) {
		this.pravoPristupa = pravoPristupa;
	}

}