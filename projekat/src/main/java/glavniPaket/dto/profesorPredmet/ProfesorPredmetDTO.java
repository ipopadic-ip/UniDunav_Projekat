package glavniPaket.dto.profesorPredmet;

import java.util.ArrayList;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.predmet.PredmetDTO;

public class ProfesorPredmetDTO {
    private Long id;
    private ProfesorDTO profesor;
    private PredmetDTO predmet;
    private List<TerminNastaveDTO> terminiNastave = new ArrayList<>();

    public ProfesorPredmetDTO() {}

    public ProfesorPredmetDTO(Long id, ProfesorDTO profesor, PredmetDTO predmet, List<TerminNastaveDTO> terminiNastave) {
        this.id = id;
        this.profesor = profesor;
        this.predmet = predmet;
        this.terminiNastave = terminiNastave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfesorDTO getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public List<TerminNastaveDTO> getTerminiNastave() {
        return terminiNastave;
    }

    public void setTerminiNastave(List<TerminNastaveDTO> terminiNastave) {
        this.terminiNastave = terminiNastave;
    }
}
