package com.unidunav.dapartman.dto;

import java.util.List;

import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.profesor.dto.ProfesorDTO;

public class DepartmanDTO {

    private Long id;
    private String naziv;
    private String opis;
    private List<KatedraDTO> katedre;
    private FakultetDTO fakultet;
    private ProfesorDTO sefDepartmana;

    public DepartmanDTO() {}

    public DepartmanDTO(Long id, String naziv, String opis, List<KatedraDTO> katedre,
                        FakultetDTO fakultet, ProfesorDTO sefDepartmana) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.katedre = katedre;
        this.fakultet = fakultet;
        this.sefDepartmana = sefDepartmana;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<KatedraDTO> getKatedre() {
        return katedre;
    }

    public void setKatedre(List<KatedraDTO> katedre) {
        this.katedre = katedre;
    }

    public FakultetDTO getFakultet() {
        return fakultet;
    }

    public void setFakultet(FakultetDTO fakultet) {
        this.fakultet = fakultet;
    }

    public ProfesorDTO getSefDepartmana() {
        return sefDepartmana;
    }

    public void setSefDepartmana(ProfesorDTO sefDepartmana) {
        this.sefDepartmana = sefDepartmana;
    }
}
