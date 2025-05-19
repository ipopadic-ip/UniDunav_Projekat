package glavniPaket.model.predmet;

import java.time.LocalDateTime;
import java.util.List;

import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "prijave_prestupa")
public class PrijavaPrestupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String opis;

    @Column(nullable = false)
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "pohadjanje_id", nullable = false)
    private PohadjanjePredmeta pohadjanje;

	public PrijavaPrestupa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PrijavaPrestupa(Long id, String opis, LocalDateTime datum, PohadjanjePredmeta pohadjanje) {
		super();
		this.id = id;
		this.opis = opis;
		this.datum = datum;
		this.pohadjanje = pohadjanje;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public LocalDateTime getDatum() {
		return datum;
	}


	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}


	public PohadjanjePredmeta getPohadjanje() {
		return pohadjanje;
	}


	public void setPohadjanje(PohadjanjePredmeta pohadjanje) {
		this.pohadjanje = pohadjanje;
	}
    
}
