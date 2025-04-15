package glavniPaket.model.fakultet;

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
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

@Entity
public class Fakultet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; 
    
    @Column(nullable=false)
    private String naziv;
    
    @Column(nullable=false)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "adresa_id", nullable = false)
    private Adresa adresa;
    
    @ManyToOne 
    @JoinColumn(name = "univerzitet_id", nullable = false)
    private Univerzitet univerzitet;
    
    @OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Katedra> katedre = new ArrayList<Katedra>();
    
    private String opis;
    
    @OneToOne
    @JoinColumn(name = "dekan_id", nullable = true)
    private Profesor dekan;

    public Fakultet() {
        super();
    }
    public Fakultet(Long id) { 
        this.id = id;
    }

    public Fakultet(Long id, String naziv, String email, Adresa adresa, Univerzitet univerzitet, ArrayList<Katedra> katedre,
                    String opis, Profesor dekan) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.adresa = adresa;
        this.univerzitet = univerzitet;
        this.katedre = katedre;
        this.opis = opis;
        this.dekan = dekan;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresa getAdresa() {
		return adresa;
	}
    
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	public Univerzitet getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(Univerzitet univerzitet) {
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

    public Profesor getDekan() {
        return dekan;
    }

    public void setDekan(Profesor dekan) {
        this.dekan = dekan;
    }
}
