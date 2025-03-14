package dto.korisnika;

import java.util.Date;

import model.korisnika.Student;

public class StudentDTO extends RegistrovaniKorisnikDTO {
    
    private Date godinaUpisa;
    private String adresa;
    private String brojIndeksa;

    public StudentDTO() {
        super();
    }

    public StudentDTO(Integer id, String ime, String prezime, String korisnickoIme, 
                      Date datumRodjenja, String mestoRodjenja, String jmbg, String email,
                      Date godinaUpisa, String adresa, String brojIndeksa) {
        super(id, ime, prezime, korisnickoIme, datumRodjenja, mestoRodjenja, jmbg, email);
        this.godinaUpisa = godinaUpisa;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
    }

    public Date getGodinaUpisa() { return godinaUpisa; }
    public void setGodinaUpisa(Date godinaUpisa) { this.godinaUpisa = godinaUpisa; }

    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }

    public String getBrojIndeksa() { return brojIndeksa; }
    public void setBrojIndeksa(String brojIndeksa) { this.brojIndeksa = brojIndeksa; }

    
    public static StudentDTO fromEntity(Student student) {
        return new StudentDTO(
            student.getId(),
            student.getIme(),
            student.getPrezime(),
            student.getKorisnickoIme(),
            student.getDatumRodjenja(),
            student.getMestoRodjenja().getNaziv(),
            student.getJmbg(),
            student.getEmail(),
            student.getGodinaUpisa(),
            student.getAdresa() != null ? student.getAdresa().getUlica() + " " + student.getAdresa().getBroj() : "N/A",
            student.getBrojIndeksa()
        );
    }
}
