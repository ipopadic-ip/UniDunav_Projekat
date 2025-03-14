package model.adresa;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Mesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    @JoinColumn(name = "drzava_id", nullable = false)
    private Drzava drzava;

    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adresa> adrese;

	public Mesto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mesto(Long id, String naziv, Drzava drzava, List<Adresa> adrese) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.drzava = drzava;
		this.adrese = adrese;
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

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public List<Adresa> getAdrese() {
		return adrese;
	}

	public void setAdrese(List<Adresa> adrese) {
		this.adrese = adrese;
	}
    
    
    
}
