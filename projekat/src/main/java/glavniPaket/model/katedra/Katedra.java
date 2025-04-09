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
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.predmet.Predmet;

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
    
    @ManyToOne // Katedra je povezana sa fakultetom
    @JoinColumn(name = "fakultet_id", nullable = true) // Može biti null ako katedra nije dodeljena fakultetu
    private Fakultet fakultet;

    public Katedra() {
        super();
    }
    public Katedra(Integer id) {
        this.id = id;
    }

    public Katedra(Integer id, String naziv, ArrayList<Predmet> predmeti, String opis, Fakultet fakultet) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.predmeti = predmeti;
        this.opis = opis;
        this.fakultet = fakultet;
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

	
	
	
	
	
}
