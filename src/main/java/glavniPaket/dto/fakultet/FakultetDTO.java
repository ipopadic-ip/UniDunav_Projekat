package glavniPaket.dto.fakultet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

public class FakultetDTO {

    private Long id;
    private String naziv;
    private String email;
    private String opis;

    private Long univerzitetId;
    private Long dekanId;
    private List<Long> departmanIds;

    public FakultetDTO() {}

    public FakultetDTO(Long id, String naziv, String email, String opis,
                       Long univerzitetId, Long dekanId, List<Long> departmanIds) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.opis = opis;
        this.univerzitetId = univerzitetId;
        this.dekanId = dekanId;
        this.departmanIds = departmanIds;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(Long univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    public Long getDekanId() {
        return dekanId;
    }

    public void setDekanId(Long dekanId) {
        this.dekanId = dekanId;
    }

    public List<Long> getDepartmanIds() {
        return departmanIds;
    }

    public void setDepartmanIds(List<Long> departmanIds) {
        this.departmanIds = departmanIds;
    }
}