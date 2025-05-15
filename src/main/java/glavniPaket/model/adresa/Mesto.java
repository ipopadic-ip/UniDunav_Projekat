package glavniPaket.model.adresa;

import jakarta.persistence.*;
import java.util.List;

import glavniPaket.dto.adresa.MestoDTO;

@Entity
public class Mesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naziv;

    @ManyToOne
    @JoinColumn(name = "drzava_id", nullable = true)
    private Drzava drzava;

    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adresa> adrese;

    public Mesto() {
        super();
    }

    public Mesto(Integer id) {
        this.id = id;
    }

    public Mesto(Integer id, String naziv, Drzava drzava, List<Adresa> adrese) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.adrese = adrese;
    }

    public Mesto(Integer id, String naziv, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
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

    // Metoda za konverziju iz Mesto entiteta u MestoDTO
    public MestoDTO toDTO() {
        return new MestoDTO(this.id, this.naziv);
    }
}