package com.unidunav.dapartman.dto;

public class DepartmanCreateUpdateDTO {
	private String naziv;
    private String opis;
    private Long fakultetId;
    private Long sefDepartmanaId;

    // Getteri i setteri
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
}
