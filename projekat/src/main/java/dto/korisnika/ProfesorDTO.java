package dto.korisnika;

import java.util.Date;
import java.util.Set;

public class ProfesorDTO extends RegistrovaniKorisnikDTO {
    private String titula;
    private String biografija;
    private Set<Integer> predmeti;
    private Integer univerzitetId;
    private Integer fakultetId;
    private Integer katedraId;

    public ProfesorDTO() {
        super();
    }

    public ProfesorDTO(Integer id, String ime, String prezime, String korisnickoIme, Date datumRodjenja,
                        String mestoRodjenja, String jmbg, String email, String titula, String biografija,
                        Set<Integer> predmeti, Integer univerzitetId, Integer fakultetId, Integer katedraId) {
        super(id, ime, prezime, korisnickoIme, datumRodjenja, mestoRodjenja, jmbg, email);
        this.titula = titula;
        this.biografija = biografija;
        this.predmeti = predmeti;
        this.univerzitetId = univerzitetId;
        this.fakultetId = fakultetId;
        this.katedraId = katedraId;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public Set<Integer> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Set<Integer> predmeti) {
        this.predmeti = predmeti;
    }

    public Integer getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(Integer univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    public Integer getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(Integer fakultetId) {
        this.fakultetId = fakultetId;
    }

    public Integer getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Integer katedraId) {
        this.katedraId = katedraId;
    }
}