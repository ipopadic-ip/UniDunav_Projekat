package glavniPaket.model.studijskiProgram;

import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.tipStudija.TipStudija;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class StudijskiProgram {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String naziv;
	
	private String opis;
	
//	private GodinaStudija godinaStudija;
	@ManyToOne
	@JoinColumn(name = "godina_studija_id")
	private GodinaStudija godinaStudija;

	
	@ManyToOne
    @JoinColumn(name = "tipStudija_id")
    private TipStudija tipStudija;
	
	@OneToOne
    @JoinColumn(name = "rukovodilac_id", nullable = true)
	private Profesor rukovodilac;

	public StudijskiProgram() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudijskiProgram(Long id, String naziv, String opis, GodinaStudija godinaStudija, TipStudija tipStudija,
			Profesor rukovodilac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.godinaStudija = godinaStudija;
		this.tipStudija = tipStudija;
		this.rukovodilac = rukovodilac;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public TipStudija getTipStudija() {
		return tipStudija;
	}

	public void setTipStudija(TipStudija tipStudija) {
		this.tipStudija = tipStudija;
	}

	public Profesor getRukovodilac() {
		return rukovodilac;
	}

	public void setRukovodilac(Profesor rukovodilac) {
		this.rukovodilac = rukovodilac;
	}
	
	
}
