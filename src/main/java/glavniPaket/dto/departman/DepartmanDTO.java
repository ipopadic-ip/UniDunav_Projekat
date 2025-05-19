package glavniPaket.dto.departman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.model.departman.Departman;

public class DepartmanDTO {

    private Long id;
    private String naziv;
    private String opis;

    private Long fakultetId;
    private Long sefDepartmanaId;
    private List<Long> katedraIds;

    public DepartmanDTO() {}

    public DepartmanDTO(Long id, String naziv, String opis,
                        Long fakultetId, Long sefDepartmanaId,
                        List<Long> katedraIds) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.fakultetId = fakultetId;
        this.sefDepartmanaId = sefDepartmanaId;
        this.katedraIds = katedraIds;
    }

    // === Getteri i setteri ===

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

    public Long getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(Long fakultetId) {
        this.fakultetId = fakultetId;
    }

    public Long getSefDepartmanaId() {
        return sefDepartmanaId;
    }

    public void setSefDepartmanaId(Long sefDepartmanaId) {
        this.sefDepartmanaId = sefDepartmanaId;
    }

    public List<Long> getKatedraIds() {
        return katedraIds;
    }

    public void setKatedraIds(List<Long> katedraIds) {
        this.katedraIds = katedraIds;
    }
}