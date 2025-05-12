package glavniPaket.dto.korisnika;

public class AdministratorDTO {
    private Long id;
    private RegistrovaniKorisnikDTO korisnik;

    public AdministratorDTO() {}

    public AdministratorDTO(Long id, RegistrovaniKorisnikDTO korisnik) {
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
