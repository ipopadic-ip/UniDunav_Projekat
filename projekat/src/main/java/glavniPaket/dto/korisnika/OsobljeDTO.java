package glavniPaket.dto.korisnika;

public class OsobljeDTO {
    private Long id;
    private RegistrovaniKorisnikDTO korisnik;

    public OsobljeDTO() {}

    public OsobljeDTO(Long id, RegistrovaniKorisnikDTO korisnik) {
        this.id = id;
        this.korisnik = korisnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrovaniKorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }
}
