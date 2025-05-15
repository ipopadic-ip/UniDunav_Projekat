package glavniPaket.model.adresa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drzava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false)
    private String naziv;

    @OneToMany(mappedBy = "drzava", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mesto> mesta = new ArrayList<>();


	public Drzava() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Drzava(Integer id, String naziv, List<Mesto> mesta) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.mesta = mesta;
	}
	public Drzava(Integer id, String naziv) {
	    this.id = id;
	    this.naziv = naziv;
	    this.mesta = new ArrayList<>(); // Prazna lista inicijalno
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

	public List<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(List<Mesto> mesta) {
		this.mesta = mesta;
	}
    

    
}