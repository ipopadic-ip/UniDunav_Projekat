package glavniPaket.model.departman;

import jakarta.persistence.*;
import java.util.ArrayList;

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;

@Entity
public class Departman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String naziv;

    private String opis;

    @OneToMany(mappedBy = "departman", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Katedra> katedre = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fakultet_id", nullable = true)
    private Fakultet fakultet;

    @OneToOne
    @JoinColumn(name = "sef_departmana_id", nullable = true)
    private Profesor sefDepartmana;

    public Departman() {}

    public Departman(Long id) {
        this.id = id;
    }

    public Departman(Long id, String naziv, String opis, ArrayList<Katedra> katedre, Fakultet fakultet, Profesor sefDepartmana) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.katedre = katedre;
        this.fakultet = fakultet;
        this.sefDepartmana = sefDepartmana;
    }

    // Getteri i setteri

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

    public ArrayList<Katedra> getKatedre() {
        return katedre;
    }

    public void setKatedre(ArrayList<Katedra> katedre) {
        this.katedre = katedre;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Profesor getSefDepartmana() {
        return sefDepartmana;
    }

    public void setSefDepartmana(Profesor sefDepartmana) {
        this.sefDepartmana = sefDepartmana;
    }
}

