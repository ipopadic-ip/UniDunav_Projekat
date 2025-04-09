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
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.univerzitet.Univerzitet;

@Entity
public class Fakultet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String naziv;
    
    @Column(nullable=false)
    private String email;
    
    @ManyToOne // Fakultet je povezan sa univerzitetom
    @JoinColumn(name = "univerzitet_id", nullable = false) // Fakultet mora imati univerzitet
    private Univerzitet univerzitet;
    
    @OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Katedra> katedre = new ArrayList<Katedra>();
    
    private String opis;

    public Fakultet() {
        super();
    }
    public Fakultet(Integer id) {
        this.id = id;
    }

    public Fakultet(Integer id, String naziv, String email, Univerzitet univerzitet, ArrayList<Katedra> katedre,
                    String opis) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.univerzitet = univerzitet;
        this.katedre = katedre;
        this.opis = opis;
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
	
	
}
