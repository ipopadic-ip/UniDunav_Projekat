package glavniPaket.dto.katedra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.katedra.Katedra;

import java.util.ArrayList;

public class KatedraDTO {

    private Long id;
    private String naziv;
    private String opis;

    private Long departmanId;
    private Long sefKatedreId;

    private List<Long> predmetIds;
    private List<Long> tipoviStudijaIds;

    public KatedraDTO() {}

    public KatedraDTO(Long id, String naziv, String opis,
                      Long departmanId, Long sefKatedreId,
                      List<Long> predmetIds, List<Long> tipoviStudijaIds) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.departmanId = departmanId;
        this.sefKatedreId = sefKatedreId;
        this.predmetIds = predmetIds;
        this.tipoviStudijaIds = tipoviStudijaIds;
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

    public Long getDepartmanId() {
        return departmanId;
    }

    public void setDepartmanId(Long departmanId) {
        this.departmanId = departmanId;
    }

    public Long getSefKatedreId() {
        return sefKatedreId;
    }

    public void setSefKatedreId(Long sefKatedreId) {
        this.sefKatedreId = sefKatedreId;
    }

    public List<Long> getPredmetIds() {
        return predmetIds;
    }

    public void setPredmetIds(List<Long> predmetIds) {
        this.predmetIds = predmetIds;
    }

    public List<Long> getTipoviStudijaIds() {
        return tipoviStudijaIds;
    }

    public void setTipoviStudijaIds(List<Long> tipoviStudijaIds) {
        this.tipoviStudijaIds = tipoviStudijaIds;
    }
}