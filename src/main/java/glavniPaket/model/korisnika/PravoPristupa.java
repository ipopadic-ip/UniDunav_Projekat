package glavniPaket.model.korisnika;

import jakarta.persistence.*;

@Entity
public class PravoPristupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv; // npr. "ADMINISTRATOR", "STUDENTSKA_SLUZBA"

    public PravoPristupa() {}

    public PravoPristupa(Long id,String naziv) {
        this.id=id;
    	this.naziv = naziv;
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
}
