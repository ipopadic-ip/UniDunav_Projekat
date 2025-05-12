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
    private RegistrovaniKorisnikDTO korisnik;
    private String titula;
    private String biografija;
    private Set<ProfesorPredmetDTO> predmeti = new HashSet<>();
    private UniverzitetDTO univerzitet;
    private FakultetDTO fakultet;
    private DepartmanDTO departman;
    private KatedraDTO katedra;
    private StudijskiProgramDTO studijskiProgram;

    public ProfesorDTO() {}

    public ProfesorDTO(Long id, RegistrovaniKorisnikDTO korisnik, String titula, String biografija,
                       Set<ProfesorPredmetDTO> predmeti, UniverzitetDTO univerzitet,
                       FakultetDTO fakultet, DepartmanDTO departman,
                       KatedraDTO katedra, StudijskiProgramDTO studijskiProgram) {
        this.id = id;
        this.korisnik = korisnik;
        this.titula = titula;
        this.biografija = biografija;
        this.predmeti = predmeti;
        this.univerzitet = univerzitet;
        this.fakultet = fakultet;
        this.departman = departman;
        this.katedra = katedra;
        this.studijskiProgram = studijskiProgram;
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

    public Set<ProfesorPredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Set<ProfesorPredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public UniverzitetDTO getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(UniverzitetDTO univerzitet) {
        this.univerzitet = univerzitet;
    }

    public FakultetDTO getFakultet() {
        return fakultet;
    }

    public void setFakultet(FakultetDTO fakultet) {
        this.fakultet = fakultet;
    }

    public DepartmanDTO getDepartman() {
        return departman;
    }

    public void setDepartman(DepartmanDTO departman) {
        this.departman = departman;
    }

    public KatedraDTO getKatedra() {
        return katedra;
    }

    public void setKatedra(KatedraDTO katedra) {
        this.katedra = katedra;
    }

    public StudijskiProgramDTO getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }
}