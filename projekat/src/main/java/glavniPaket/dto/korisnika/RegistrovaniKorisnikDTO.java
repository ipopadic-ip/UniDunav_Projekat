package glavniPaket.dto.korisnika;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<DodeljenoPravoPristupaDTO> pravaPristupa = new HashSet<>();

    public RegistrovaniKorisnikDTO() {}

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme,
                                   Date datumRodjenja, MestoDTO mestoRodjenja, String jmbg,
                                   String email, Set<DodeljenoPravoPristupaDTO> pravaPristupa) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.jmbg = jmbg;
        this.email = email;
        this.pravaPristupa = pravaPristupa;
    }
    
    public RegistrovaniKorisnikDTO(RegistrovaniKorisnik registrovanikorisnik) {
        this.id = registrovanikorisnik.getId();
        this.ime = registrovanikorisnik.getIme();
        this.prezime = registrovanikorisnik.getPrezime();
        this.korisnickoIme = registrovanikorisnik.getKorisnickoIme();
        this.datumRodjenja = registrovanikorisnik.getDatumRodjenja();
        this.mestoRodjenja = registrovanikorisnik.getMestoRodjenja() != null 
                ? new MestoDTO(registrovanikorisnik.getMestoRodjenja()) : null;
        this.jmbg = registrovanikorisnik.getJmbg();
        this.email = registrovanikorisnik.getEmail();
        this.pravaPristupa = registrovanikorisnik.getPravaPristupa() != null
                ? registrovanikorisnik.getPravaPristupa().stream()
                    .map(DodeljenoPravoPristupaDTO::new)
                    .collect(Collectors.toSet())
                : new HashSet<>();
    }

    public static RegistrovaniKorisnikDTO fromEntity(RegistrovaniKorisnik registrovaniKorisnik) {
        RegistrovaniKorisnikDTO dto = new RegistrovaniKorisnikDTO();
        dto.setId(registrovaniKorisnik.getId());
        dto.setIme(registrovaniKorisnik.getIme());
        dto.setPrezime(registrovaniKorisnik.getPrezime());
        dto.setKorisnickoIme(registrovaniKorisnik.getKorisnickoIme());
        dto.setDatumRodjenja(registrovaniKorisnik.getDatumRodjenja());
        dto.setMestoRodjenja(registrovaniKorisnik.getMestoRodjenja() != null 
                ? new MestoDTO(registrovaniKorisnik.getMestoRodjenja()) : null);
        dto.setJmbg(registrovaniKorisnik.getJmbg());
        dto.setEmail(registrovaniKorisnik.getEmail());
        dto.setPravaPristupa(registrovaniKorisnik.getPravaPristupa() != null
                ? registrovaniKorisnik.getPravaPristupa().stream()
                    .map(DodeljenoPravoPristupaDTO::new)
                    .collect(Collectors.toSet())
                : new HashSet<>());
        return dto;
    }
    
    public RegistrovaniKorisnik toEntity() {
        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
        korisnik.setId(this.id);
        korisnik.setIme(this.ime);
        korisnik.setPrezime(this.prezime);
        korisnik.setKorisnickoIme(this.korisnickoIme);
        korisnik.setDatumRodjenja(this.datumRodjenja);
        korisnik.setJmbg(this.jmbg);
        korisnik.setEmail(this.email);
        // Optionally handle setting Mesto
        if (this.mestoRodjenja != null) {
            Mesto mesto = new Mesto();
            mesto.setId(this.mestoRodjenja.getId());
            mesto.setNaziv(this.mestoRodjenja.getNaziv());
            korisnik.setMestoRodjenja(mesto);
        }
        // PravaPristupa conversion if needed
        return korisnik;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public MestoDTO getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(MestoDTO mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<DodeljenoPravoPristupaDTO> getPravaPristupa() {
        return pravaPristupa;
    }

    public void setPravaPristupa(Set<DodeljenoPravoPristupaDTO> pravaPristupa) {
        this.pravaPristupa = pravaPristupa;
    }
}