package com.unidunav.fakultet.dto;

import java.util.List;

import com.unidunav.dapartman.dto.DepartmanDTO;
import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.univerzitet.dto.UniverzitetDTO;

public class FakultetDTO {

    private Long id;
    private String naziv;
    private String email;
    private UniverzitetDTO univerzitet;
    private ProfesorDTO dekan;
    private List<DepartmanDTO> departmani;
    private String opis;

    public FakultetDTO() {}

    public FakultetDTO(Long id, String naziv, String email, UniverzitetDTO univerzitet,
                       ProfesorDTO dekan, List<DepartmanDTO> departmani, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.departmani = departmani;
        this.opis = opis;
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

    public UniverzitetDTO getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(UniverzitetDTO univerzitet) {
        this.univerzitet = univerzitet;
    }

    public ProfesorDTO getDekan() {
        return dekan;
    }

    public void setDekan(ProfesorDTO dekan) {
        this.dekan = dekan;
    }

    public List<DepartmanDTO> getDepartmani() {
        return departmani;
    }

    public void setDepartmani(List<DepartmanDTO> departmani) {
        this.departmani = departmani;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
