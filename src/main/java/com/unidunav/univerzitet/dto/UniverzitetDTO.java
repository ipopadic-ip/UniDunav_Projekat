package com.unidunav.univerzitet.dto;

import java.util.List;

import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.profesor.dto.ProfesorDTO;

public class UniverzitetDTO {

    private Long id;
    private String naziv;
    private String email;
    private String brojTelefona;
    private String opis;
    private List<FakultetDTO> fakulteti;
    private ProfesorDTO rektor;

    public UniverzitetDTO() {
    }

    public UniverzitetDTO(Long id, String naziv, String email, String brojTelefona, String opis, List<FakultetDTO> fakulteti, ProfesorDTO rektor) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.opis = opis;
        this.fakulteti = fakulteti;
        this.rektor = rektor;
    }

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

    public List<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(List<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public ProfesorDTO getRektor() {
        return rektor;
    }

    public void setRektor(ProfesorDTO rektor) {
        this.rektor = rektor;
    }
}
