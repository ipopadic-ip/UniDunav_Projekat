package glavniPaket.dto.korisnika;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;

public class RegistrovaniKorisnikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private Date datumRodjenja;
    private String jmbg;
    private String lozinka;
    private String email;

    private Long mestoRodjenjaId;

    private Long studentId;
    private Long profesorId;

    public RegistrovaniKorisnikDTO() {}

    public RegistrovaniKorisnikDTO(Long id, String ime, String prezime, String korisnickoIme,
                                   Date datumRodjenja, String jmbg, String lozinka, String email,
                                   Long mestoRodjenjaId, Long studentId, Long profesorId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.lozinka = lozinka;
        this.email = email;
        this.mestoRodjenjaId = mestoRodjenjaId;
        this.studentId = studentId;
        this.profesorId = profesorId;
    }

    // === GETTERI I SETTERI ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMestoRodjenjaId() {
        return mestoRodjenjaId;
    }

    public void setMestoRodjenjaId(Long mestoRodjenjaId) {
        this.mestoRodjenjaId = mestoRodjenjaId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }
}