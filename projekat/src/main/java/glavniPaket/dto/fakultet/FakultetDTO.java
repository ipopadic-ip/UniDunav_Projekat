package glavniPaket.dto.fakultet;

import java.util.ArrayList;

import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

public class FakultetDTO {
    private Long id; 
    private String naziv;
    private String email;
    private Adresa adresa;
    private Univerzitet univerzitet;
    private ArrayList<Katedra> katedre = new ArrayList<>();
    private String opis;
    private Profesor dekan;

    public FakultetDTO() {}

    public FakultetDTO(Long id, String naziv, String email, Adresa adresa, Univerzitet univerzitet, ArrayList<Katedra> katedre, String opis, Profesor dekan) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.adresa = adresa;
        this.univerzitet = univerzitet;
        this.katedre = katedre;
        this.opis = opis;
        this.dekan = dekan;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Adresa getAdresa() { return adresa; }
    public void setAdresa(Adresa adresa) { this.adresa = adresa; }

    public Univerzitet getUniverzitet() { return univerzitet; }
    public void setUniverzitet(Univerzitet univerzitet) { this.univerzitet = univerzitet; }

    public ArrayList<Katedra> getKatedre() { return katedre; }
    public void setKatedre(ArrayList<Katedra> katedre) { this.katedre = katedre; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public Profesor getDekan() { return dekan; }
    public void setDekan(Profesor dekan) { this.dekan = dekan; }

    
    public Fakultet toEntity() {
        return new Fakultet(
            this.id,
            this.naziv,
            this.email,
            this.adresa, 
            this.univerzitet, 
            this.katedre,
            this.opis,
            this.dekan 
        );
    }

    
    public static FakultetDTO fromEntity(Fakultet f) {
        if (f == null) {
            return null;
        }
        return new FakultetDTO(
            f.getId(),
            f.getNaziv(),
            f.getEmail(),
            f.getAdresa(),
            f.getUniverzitet(), 
            new ArrayList<>(f.getKatedre()), 
            f.getOpis(),
            f.getDekan() 
        );
    }
}
