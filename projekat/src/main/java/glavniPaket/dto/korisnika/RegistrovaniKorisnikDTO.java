package glavniPaket.dto.korisnika;

import java.util.Date;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;

public class RegistrovaniKorisnikDTO {

    private Integer id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private Date datumRodjenja;
    private MestoDTO mestoRodjenja;
    private String jmbg;
    private String email;

    public RegistrovaniKorisnikDTO() {
        super();
    }

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme,
                                   Date datumRodjenja, MestoDTO mestoRodjenja, String jmbg, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.jmbg = jmbg;
        this.email = email;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getKorisnickoIme() { return korisnickoIme; }
    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }

    public Date getDatumRodjenja() { return datumRodjenja; }
    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }

    public MestoDTO getMestoRodjenja() { return mestoRodjenja; }
    public void setMestoRodjenja(MestoDTO mestoRodjenja) { this.mestoRodjenja = mestoRodjenja; }

    public String getJmbg() { return jmbg; }
    public void setJmbg(String jmbg) { this.jmbg = jmbg; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public static RegistrovaniKorisnikDTO fromEntity(RegistrovaniKorisnik korisnik) {
        return new RegistrovaniKorisnikDTO(
            korisnik.getId(),
            korisnik.getIme(),
            korisnik.getPrezime(),
            korisnik.getKorisnickoIme(),
            korisnik.getDatumRodjenja(),
            korisnik.getMestoRodjenja() != null ? new MestoDTO(korisnik.getMestoRodjenja()) : null,
            korisnik.getJmbg(),
            korisnik.getEmail()
        );
    }

    public RegistrovaniKorisnik toEntity() {
        Mesto mesto = mestoRodjenja != null
            ? new Mesto(mestoRodjenja.getId(), mestoRodjenja.getNaziv(), null)
            : null;

        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
        korisnik.setId(id);
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setKorisnickoIme(korisnickoIme);
        korisnik.setDatumRodjenja(datumRodjenja);
        korisnik.setMestoRodjenja(mesto);
        korisnik.setJmbg(jmbg);
        korisnik.setEmail(email);

        return korisnik;
    }
}