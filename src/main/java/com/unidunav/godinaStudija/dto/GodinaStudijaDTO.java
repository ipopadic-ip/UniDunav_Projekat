package com.unidunav.godinaStudija.dto;

import java.util.List;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;

public class GodinaStudijaDTO {

    private Long id;
    private int godina;
    private StudijskiProgramDTO studijskiProgram;
    private List<PredmetDTO> predmeti;

    public GodinaStudijaDTO() {
    }

    public GodinaStudijaDTO(Long id, int godina, StudijskiProgramDTO studijskiProgram, List<PredmetDTO> predmeti) {
        this.id = id;
        this.godina = godina;
        this.studijskiProgram = studijskiProgram;
        this.predmeti = predmeti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public StudijskiProgramDTO getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }
}
