package glavniPaket.dto.studijskiProgram;

import java.util.List;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.model.tipStudija.TipStudija;

public class StudijskiProgramDTO {

    private Long id;
    private String naziv;
    private String opis;

    private Long tipStudijaId;
    private Long rukovodilacId;

    private List<Long> godineStudijaIds;

    public StudijskiProgramDTO() {}

    public StudijskiProgramDTO(Long id, String naziv, String opis,
                               Long tipStudijaId, Long rukovodilacId,
                               List<Long> godineStudijaIds) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.tipStudijaId = tipStudijaId;
        this.rukovodilacId = rukovodilacId;
        this.godineStudijaIds = godineStudijaIds;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getTipStudijaId() {
        return tipStudijaId;
    }

    public void setTipStudijaId(Long tipStudijaId) {
        this.tipStudijaId = tipStudijaId;
    }

    public Long getRukovodilacId() {
        return rukovodilacId;
    }

    public void setRukovodilacId(Long rukovodilacId) {
        this.rukovodilacId = rukovodilacId;
    }

    public List<Long> getGodineStudijaIds() {
        return godineStudijaIds;
    }

    public void setGodineStudijaIds(List<Long> godineStudijaIds) {
        this.godineStudijaIds = godineStudijaIds;
    }
}