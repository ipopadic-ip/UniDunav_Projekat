package glavniPaket.dto.korisnika;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.profesorPredmet.ProfesorPredmetDTO;
import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.univerzitet.Univerzitet;

public class ProfesorDTO {

    private Long id;
    private String titula;
    private String biografija;

    private Long korisnikId;  // kompozicija
    private Set<Long> profesorPredmetIds; // povezani predmeti

    private Long univerzitetId;        // ako je rektor
    private Long fakultetId;           // ako je dekan
    private Long departmanId;          // ako je šef departmana
    private Long katedraId;            // ako je šef katedre
    private Long studijskiProgramId;   // ako je rukovodilac

    public ProfesorDTO() {}

    public ProfesorDTO(Long id, String titula, String biografija,
                       Long korisnikId, Set<Long> profesorPredmetIds,
                       Long univerzitetId, Long fakultetId, Long departmanId,
                       Long katedraId, Long studijskiProgramId) {
        this.id = id;
        this.titula = titula;
        this.biografija = biografija;
        this.korisnikId = korisnikId;
        this.profesorPredmetIds = profesorPredmetIds;
        this.univerzitetId = univerzitetId;
        this.fakultetId = fakultetId;
        this.departmanId = departmanId;
        this.katedraId = katedraId;
        this.studijskiProgramId = studijskiProgramId;
    }

    // === GETTERI I SETTERI ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Set<Long> getProfesorPredmetIds() {
        return profesorPredmetIds;
    }

    public void setProfesorPredmetIds(Set<Long> profesorPredmetIds) {
        this.profesorPredmetIds = profesorPredmetIds;
    }

    public Long getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(Long univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    public Long getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(Long fakultetId) {
        this.fakultetId = fakultetId;
    }

    public Long getDepartmanId() {
        return departmanId;
    }

    public void setDepartmanId(Long departmanId) {
        this.departmanId = departmanId;
    }

    public Long getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Long katedraId) {
        this.katedraId = katedraId;
    }

    public Long getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Long studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }
}