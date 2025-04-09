package glavniPaket.dto.korisnika;



import java.util.Date;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;

public class StudentDTO extends RegistrovaniKorisnikDTO {

    private Date godinaUpisa;
    private AdresaDTO adresa;
    private String brojIndeksa;
    private MestoDTO mestoRodjenja;

    public StudentDTO() {
        super();
    }

    public StudentDTO(Integer id, String ime, String prezime, String korisnickoIme,
                      Date datumRodjenja, MestoDTO mestoRodjenja, String jmbg,
                      String email, Date godinaUpisa, AdresaDTO adresa, String brojIndeksa) {
        super(id, ime, prezime, korisnickoIme, datumRodjenja, mestoRodjenja, jmbg, email);
        this.godinaUpisa = godinaUpisa;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
        this.mestoRodjenja = mestoRodjenja;
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

    public MestoDTO getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(MestoDTO mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public static StudentDTO fromEntity(Student student) {
        if (student == null || student.getKorisnik() == null) return null;

        RegistrovaniKorisnik k = student.getKorisnik();

        return new StudentDTO(
            student.getId(),
            k.getIme(),
            k.getPrezime(),
            k.getKorisnickoIme(),
            k.getDatumRodjenja(),
            k.getMestoRodjenja() != null ? new MestoDTO(k.getMestoRodjenja()) : null,
            k.getJmbg(),
            k.getEmail(),
            student.getGodinaUpisa(),
            student.getAdresa() != null ? new AdresaDTO(
                student.getAdresa().getId(),
                student.getAdresa().getUlica(),
                student.getAdresa().getBroj(),
                student.getAdresa().getMesto() != null ? new MestoDTO(student.getAdresa().getMesto()) : null
            ) : null,
            student.getBrojIndeksa()
        );
    }

    public Student toStudentEntity() {
        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
        korisnik.setId(getId());
        korisnik.setIme(getIme());
        korisnik.setPrezime(getPrezime());
        korisnik.setKorisnickoIme(getKorisnickoIme());
        korisnik.setDatumRodjenja(getDatumRodjenja());
        korisnik.setJmbg(getJmbg());
        korisnik.setEmail(getEmail());

        if (mestoRodjenja != null) {
            korisnik.setMestoRodjenja(new Mesto(mestoRodjenja.getId(), mestoRodjenja.getNaziv(), null));
        }

        Student student = new Student();
        student.setKorisnik(korisnik);
        student.setGodinaUpisa(godinaUpisa);

        if (adresa != null) {
            student.setAdresa(new Adresa(
                adresa.getId(),
                adresa.getUlica(),
                adresa.getBroj(),
                adresa.getMesto() != null ? new Mesto(adresa.getMesto().getId(), adresa.getMesto().getNaziv(), null) : null
            ));
        }

        student.setBrojIndeksa(brojIndeksa);

        return student;
    }
}
