package com.unidunav.studijskiProgram.dto;

import java.util.List;

import com.unidunav.godinaStudija.dto.GodinaStudijaDTO;
import com.unidunav.profesor.dto.ProfesorDTO;
import com.unidunav.tipStudija.dto.TipStudijaDTO;

public class StudijskiProgramDTO {

    private Long id;
    private String naziv;
    private String opis;

    private List<GodinaStudijaDTO> godineStudija;
    private TipStudijaDTO tipStudija;
    private ProfesorDTO rukovodilac;
    
    private Long katedraId;


    public StudijskiProgramDTO() {}

    public StudijskiProgramDTO(Long id, String naziv, String opis,
                                List<GodinaStudijaDTO> godineStudija,
                                TipStudijaDTO tipStudija,
                                ProfesorDTO rukovodilac) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.godineStudija = godineStudija;
        this.tipStudija = tipStudija;
        this.rukovodilac = rukovodilac;
    }

    // === Getteri i setteri ===

    public Long getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Long katedraId) {
        this.katedraId = katedraId;
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

    public List<GodinaStudijaDTO> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(List<GodinaStudijaDTO> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public TipStudijaDTO getTipStudija() {
        return tipStudija;
    }

    public void setTipStudija(TipStudijaDTO tipStudija) {
        this.tipStudija = tipStudija;
    }

    public ProfesorDTO getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(ProfesorDTO rukovodilac) {
        this.rukovodilac = rukovodilac;
    }
}
