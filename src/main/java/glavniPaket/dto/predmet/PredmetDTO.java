package glavniPaket.dto.predmet;

import java.util.ArrayList;
import java.util.List;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.profesorPredmet.ProfesorPredmetDTO;
import glavniPaket.model.predmet.Predmet;

import java.util.List;

public class PredmetDTO {

    private Long id;
    private String naziv;
    private int ects;
    private String informacijeOPredmetu;

    private Long godinaStudijaId;
    private Long silabusId;

    private List<Long> obavestenjeIds;
    private List<Long> profesorIds;
    private List<Long> pohadjanjeIds;

    public PredmetDTO() {}

    public PredmetDTO(Long id, String naziv, int ects, String informacijeOPredmetu,
                      Long godinaStudijaId, Long silabusId,
                      List<Long> obavestenjeIds,
                      List<Long> profesorIds,
                      List<Long> pohadjanjeIds) {
        this.id = id;
        this.naziv = naziv;
        this.ects = ects;
        this.informacijeOPredmetu = informacijeOPredmetu;
        this.godinaStudijaId = godinaStudijaId;
        this.silabusId = silabusId;
        this.obavestenjeIds = obavestenjeIds;
        this.profesorIds = profesorIds;
        this.pohadjanjeIds = pohadjanjeIds;
    }

    // === GETTERI I SETTERI ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getInformacijeOPredmetu() {
        return informacijeOPredmetu;
    }

    public void setInformacijeOPredmetu(String informacijeOPredmetu) {
        this.informacijeOPredmetu = informacijeOPredmetu;
    }

    public Long getGodinaStudijaId() {
        return godinaStudijaId;
    }

    public void setGodinaStudijaId(Long godinaStudijaId) {
        this.godinaStudijaId = godinaStudijaId;
    }

    public Long getSilabusId() {
        return silabusId;
    }

    public void setSilabusId(Long silabusId) {
        this.silabusId = silabusId;
    }

    public List<Long> getObavestenjeIds() {
        return obavestenjeIds;
    }

    public void setObavestenjeIds(List<Long> obavestenjeIds) {
        this.obavestenjeIds = obavestenjeIds;
    }

    public List<Long> getProfesorIds() {
        return profesorIds;
    }

    public void setProfesorIds(List<Long> profesorIds) {
        this.profesorIds = profesorIds;
    }

    public List<Long> getPohadjanjeIds() {
        return pohadjanjeIds;
    }

    public void setPohadjanjeIds(List<Long> pohadjanjeIds) {
        this.pohadjanjeIds = pohadjanjeIds;
    }
}
