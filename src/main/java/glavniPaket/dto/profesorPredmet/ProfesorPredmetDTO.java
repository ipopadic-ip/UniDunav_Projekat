package glavniPaket.dto.profesorPredmet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;

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
    
    public ProfesorPredmetDTO(ProfesorPredmet pp) {
        this.id = pp.getId();
        this.profesor = pp.getProfesor() != null ? new ProfesorDTO(pp.getProfesor()) : null;
        this.predmet = pp.getPredmet() != null ? new PredmetDTO(pp.getPredmet()) : null;
        this.terminiNastave = pp.getTerminiNastave() != null 
        	    ? pp.getTerminiNastave().stream().map(TerminNastaveDTO::new).collect(Collectors.toList())
        	    : new ArrayList<>();
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
    
    public static ProfesorPredmetDTO fromEntity(ProfesorPredmet pp) {
        return new ProfesorPredmetDTO(pp);
    }
    
    public ProfesorPredmet toEntity() {
        ProfesorPredmet profesorPredmet = new ProfesorPredmet();
        profesorPredmet.setId(this.id);
        profesorPredmet.setProfesor(this.profesor != null ? this.profesor.toEntity() : null);
        profesorPredmet.setPredmet(this.predmet != null ? this.predmet.toEntity() : null);
        profesorPredmet.setTerminiNastave(this.terminiNastave != null 
            ? this.terminiNastave.stream().map(TerminNastaveDTO::toEntity).collect(Collectors.toList())
            : null);
        return profesorPredmet;
    }

    
}
