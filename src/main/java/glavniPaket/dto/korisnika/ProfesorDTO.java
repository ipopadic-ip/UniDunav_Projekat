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
    
    public ProfesorDTO(Profesor profesor) {
        this.id = profesor.getId();
        this.korisnik = profesor.getKorisnik() != null ? new RegistrovaniKorisnikDTO(profesor.getKorisnik()) : null;
        this.titula = profesor.getTitula();
        this.biografija = profesor.getBiografija();
        this.predmeti = profesor.getPredmeti() != null
                ? profesor.getPredmeti().stream().map(ProfesorPredmetDTO::fromEntity).collect(Collectors.toSet())
                : new HashSet<>();
        this.univerzitet = profesor.getUniverzitet() != null ? new UniverzitetDTO(profesor.getUniverzitet()) : null;
        this.fakultet = profesor.getFakultet() != null ? new FakultetDTO(profesor.getFakultet()) : null;
        this.departman = profesor.getDepartman() != null ? new DepartmanDTO(profesor.getDepartman()) : null;
        this.katedra = profesor.getKatedra() != null ? new KatedraDTO(profesor.getKatedra()) : null;
        this.studijskiProgram = profesor.getStudijskiProgram() != null ? new StudijskiProgramDTO(profesor.getStudijskiProgram()) : null;
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
    
    public Profesor toEntity() {
        Profesor profesor = new Profesor();
        profesor.setId(this.id);

        // Pretvaramo RegistrovaniKorisnikDTO u RegistrovaniKorisnik entitet
        if (this.korisnik != null) {
            profesor.setKorisnik(this.korisnik.toEntity());
        }

        profesor.setTitula(this.titula);
        profesor.setBiografija(this.biografija);

        // Pretvaramo Set<ProfesorPredmetDTO> u Set<ProfesorPredmet> entitete
        if (this.predmeti != null) {
            profesor.setPredmeti(
                this.predmeti.stream()
                    .map(ProfesorPredmetDTO::toEntity)
                    .collect(Collectors.toSet())
            );
        }

        // Pretvaramo UniverzitetDTO u Univerzitet entitet
        if (this.univerzitet != null) {
            profesor.setUniverzitet(this.univerzitet.toEntity());
        }


        if (this.fakultet != null) {
            profesor.setFakultet(this.fakultet.toEntity());
        }


        if (this.departman != null) {
            profesor.setDepartman(this.departman.toEntity());
        }


        if (this.katedra != null) {
            profesor.setKatedra(this.katedra.toEntity());
        }


        if (this.studijskiProgram != null) {
            profesor.setStudijskiProgram(this.studijskiProgram.toEntity());
        }

        return profesor;
    }

}