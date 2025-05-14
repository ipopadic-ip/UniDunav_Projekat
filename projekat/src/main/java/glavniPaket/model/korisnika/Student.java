package glavniPaket.model.korisnika;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Table;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.predmet.PohadjanjePredmeta;

@Entity
@Table(name = "studenti")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Promenjen tip sa Integer na Long

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private RegistrovaniKorisnik korisnik;

    @Column(nullable = false)
    private Date godinaUpisa;

    @ManyToOne
    @JoinColumn(name = "adresa_id", nullable = false)
    private Adresa adresa;

    @Column(nullable = false, unique = true)
    private String brojIndeksa;
    
    @Column(nullable = false, unique = true)
    private int ukupanBrojECTS;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(Long id, RegistrovaniKorisnik korisnik, Date godinaUpisa, Adresa adresa, String brojIndeksa,
                   int ukupanBrojECTS, List<PohadjanjePredmeta> pohadjanja) {
        super();
        this.id = id;
        this.korisnik = korisnik;
        this.godinaUpisa = godinaUpisa;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
        this.ukupanBrojECTS = ukupanBrojECTS;
        this.pohadjanja = pohadjanja;
    }

    public Long getId() {  // Promenjen tip sa Integer na Long
        return id;
    }

    public void setId(Long id) {  // Promenjen tip sa Integer na Long
        this.id = id;
    }

    public RegistrovaniKorisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Date getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(Date godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public int getUkupanBrojECTS() {
        return ukupanBrojECTS;
    }

    public void setUkupanBrojECTS(int ukupanBrojECTS) {
        this.ukupanBrojECTS = ukupanBrojECTS;
    }

    public List<PohadjanjePredmeta> getPohadjanja() {
        return pohadjanja;
    }

    public void setPohadjanja(List<PohadjanjePredmeta> pohadjanja) {
        this.pohadjanja = pohadjanja;
    }
}
