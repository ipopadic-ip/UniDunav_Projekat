package glavniPaket.dto.univerzitet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.fakultet.FakultetDTO;

public class UniverzitetDTO {

    private Long id;
    private String naziv;
    private String email;
    private String brojTelefona;
    private String opis;

    // Samo ID adrese i rektora se šalju, ne ceo objekat
    private Long adresaId;
    private Long rektorId;

    // Lista ID-jeva fakulteta ako ti treba (opciono)
    private List<Long> fakultetIds;

    public UniverzitetDTO() {
    }

    public UniverzitetDTO(Long id, String naziv, String email, String brojTelefona, String opis,
                          Long adresaId, Long rektorId, List<Long> fakultetIds) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.opis = opis;
        this.adresaId = adresaId;
        this.rektorId = rektorId;
        this.fakultetIds = fakultetIds;
    }

    // Getteri i setteri

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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getAdresaId() {
        return adresaId;
    }

    public void setAdresaId(Long adresaId) {
        this.adresaId = adresaId;
    }

    public Long getRektorId() {
        return rektorId;
    }

    public void setRektorId(Long rektorId) {
        this.rektorId = rektorId;
    }

    public List<Long> getFakultetIds() {
        return fakultetIds;
    }

    public void setFakultetIds(List<Long> fakultetIds) {
        this.fakultetIds = fakultetIds;
    }
}