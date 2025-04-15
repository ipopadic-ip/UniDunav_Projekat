package glavniPaket.model.katedra;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.tipStudija.TipStudija;

@Entity
public class Katedra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String naziv;
    
    @OneToMany(mappedBy = "katedra", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
    
    private String opis;
    
    @ManyToOne
    @JoinColumn(name = "fakultet_id", nullable = true)
    private Fakultet fakultet;
    
    @OneToMany(mappedBy = "katedra", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<TipStudija> tipoviStudija = new ArrayList<TipStudija>();

    @OneToOne
    @JoinColumn(name = "sefKatedre_id", nullable = true)
    private Profesor sefKatedre;
    
    public Katedra() {
        super();
    }
    public Katedra(Integer id) {
        this.id = id;
    }
    
    public Katedra(Integer id, String naziv, ArrayList<Predmet> predmeti, String opis, Fakultet fakultet, ArrayList<TipStudija> tipoviStudija, Profesor sefKatedre) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.predmeti = predmeti;
        this.opis = opis;
        this.fakultet = fakultet;
        this.tipoviStudija = tipoviStudija;
        this.sefKatedre = sefKatedre;
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

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Fakultet getFakultet() {
		return fakultet;
	}

	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}
	public ArrayList<TipStudija> getTipoviStudija() {
		return tipoviStudija;
	}
	public void setTipoviStudija(ArrayList<TipStudija> tipoviStudija) {
		this.tipoviStudija = tipoviStudija;
	}
	public Profesor getSefKatedre() {
		return sefKatedre;
	}
	public void setSefKatedre(Profesor sefKatedre) {
		this.sefKatedre = sefKatedre;
	}

	
	
	
	
	
}
