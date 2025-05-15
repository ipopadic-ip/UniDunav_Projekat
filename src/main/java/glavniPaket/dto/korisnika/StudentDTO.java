package glavniPaket.dto.korisnika;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;
import glavniPaket.model.predmet.PohadjanjePredmeta;
import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;

public class StudentDTO {
    private Long id;  // Promenjen tip sa Integer na Long
    private RegistrovaniKorisnikDTO korisnik;
    private Date godinaUpisa;
    private AdresaDTO adresa;
    private String brojIndeksa;
    private int ukupanBrojECTS;
    private List<PohadjanjePredmetaDTO> pohadjanja = new ArrayList<>();

    public StudentDTO() {}

    public StudentDTO(Long id, RegistrovaniKorisnikDTO korisnik, Date godinaUpisa, AdresaDTO adresa,
                      String brojIndeksa, int ukupanBrojECTS, List<PohadjanjePredmetaDTO> pohadjanja) {
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

    public RegistrovaniKorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public Date getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(Date godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
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

    public List<PohadjanjePredmetaDTO> getPohadjanja() {
        return pohadjanja;
    }

    public void setPohadjanja(List<PohadjanjePredmetaDTO> pohadjanja) {
        this.pohadjanja = pohadjanja;
    }
    
    public String getIme() {
        return korisnik != null ? korisnik.getIme() : null;
    }

    public void setIme(String ime) {
        if (korisnik != null) {
            korisnik.setIme(ime);
        }
    }

    public String getPrezime() {
        return korisnik != null ? korisnik.getPrezime() : null;
    }

    public void setPrezime(String prezime) {
        if (korisnik != null) {
            korisnik.setPrezime(prezime);
        }
    }

    public String getKorisnickoIme() {
        return korisnik != null ? korisnik.getKorisnickoIme() : null;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        if (korisnik != null) {
            korisnik.setKorisnickoIme(korisnickoIme);
        }
    }

    public Date getDatumRodjenja() {
        return korisnik != null ? korisnik.getDatumRodjenja() : null;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        if (korisnik != null) {
            korisnik.setDatumRodjenja(datumRodjenja);
        }
    }

    public String getJmbg() {
        return korisnik != null ? korisnik.getJmbg() : null;
    }

    public void setJmbg(String jmbg) {
        if (korisnik != null) {
            korisnik.setJmbg(jmbg);
        }
    }

    public String getEmail() {
        return korisnik != null ? korisnik.getEmail() : null;
    }

    public void setEmail(String email) {
        if (korisnik != null) {
            korisnik.setEmail(email);
        }
    }

    public MestoDTO getMestoRodjenja() {
        return korisnik != null ? korisnik.getMestoRodjenja() : null;
    }

    public void setMestoRodjenja(MestoDTO mestoRodjenja) {
        if (korisnik != null) {
            korisnik.setMestoRodjenja(mestoRodjenja);
        }
    }
    

    // Convert DTO to Entity
    public Student toStudentEntity() {
        Student student = new Student();
        student.setId(this.id);  // Promenjen tip sa Integer na Long
        student.setGodinaUpisa(this.godinaUpisa);
        student.setBrojIndeksa(this.brojIndeksa);
        student.setUkupanBrojECTS(this.ukupanBrojECTS);

        // Convert RegistrovaniKorisnikDTO to RegistrovaniKorisnik entity
        if (this.korisnik != null) {
            RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
            korisnik.setId(this.korisnik.getId());  // Promenjen tip sa Integer na Long
            korisnik.setIme(this.korisnik.getIme());
            korisnik.setPrezime(this.korisnik.getPrezime());
            korisnik.setKorisnickoIme(this.korisnik.getKorisnickoIme());
            korisnik.setDatumRodjenja(this.korisnik.getDatumRodjenja());
            korisnik.setJmbg(this.korisnik.getJmbg());
            korisnik.setEmail(this.korisnik.getEmail());
            student.setKorisnik(korisnik);
        }

        // Convert AdresaDTO to Adresa entity
        if (this.adresa != null) {
            Adresa adresa = new Adresa();
            adresa.setId(this.adresa.getId());  // Promenjen tip sa Integer na Long
            adresa.setUlica(this.adresa.getUlica());
            adresa.setBroj(this.adresa.getBroj());
            if (this.adresa.getMesto() != null) {
                Mesto mesto = new Mesto();
                mesto.setId(this.adresa.getMesto().getId());  // Promenjen tip sa Integer na Long
                mesto.setNaziv(this.adresa.getMesto().getNaziv());
                adresa.setMesto(mesto);
            }
            student.setAdresa(adresa);
        }

        return student;
    }

    // Convert Entity to DTO
    public static StudentDTO fromEntity(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());  // Promenjen tip sa Integer na Long
        dto.setGodinaUpisa(student.getGodinaUpisa());
        dto.setBrojIndeksa(student.getBrojIndeksa());
        dto.setUkupanBrojECTS(student.getUkupanBrojECTS());

        // Convert RegistrovaniKorisnik entity to DTO
        if (student.getKorisnik() != null) {
            RegistrovaniKorisnikDTO korisnikDTO = new RegistrovaniKorisnikDTO(student.getKorisnik());
            dto.setKorisnik(korisnikDTO);
        }

        // Convert Adresa entity to DTO
        if (student.getAdresa() != null) {
            AdresaDTO adresaDTO = new AdresaDTO(student.getAdresa());
            dto.setAdresa(adresaDTO);
        }

        return dto;
    }
}
