package dto.korisnika;

import java.util.Date;

import model.korisnika.RegistrovaniKorisnik;

public class RegistrovaniKorisnikDTO {
    
    private Integer id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private Date datumRodjenja;
    private String mestoRodjenja; 
    private String jmbg;
    private String email;

    public RegistrovaniKorisnikDTO() {
        super();
    }

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme, 
                                   Date datumRodjenja, String mestoRodjenja, String jmbg, String email) {
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

    public String getMestoRodjenja() { return mestoRodjenja; }
    public void setMestoRodjenja(String mestoRodjenja) { this.mestoRodjenja = mestoRodjenja; }

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
            korisnik.getDatumRodnjenja(),
            korisnik.getMestoRodjenja().getNaziv(), 
            korisnik.getJmbg(),
            korisnik.getEmail()
        );
    }
}
