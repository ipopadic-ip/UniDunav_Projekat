package com.unidunav.tipStudija.dto;

import java.util.List;

import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.studijskiProgram.dto.StudijskiProgramDTO;

public class TipStudijaDTO {

    private Long id;
    private String tip;
    private KatedraDTO katedra;
    private List<StudijskiProgramDTO> studijskiProgrami;
    private boolean deleted;
    private String uri;
    
    
    
    

    public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public TipStudijaDTO() {}

    public TipStudijaDTO(Long id, String tip, KatedraDTO katedra, List<StudijskiProgramDTO> studijskiProgrami) {
        this.id = id;
        this.tip = tip;
        this.katedra = katedra;
        this.studijskiProgrami = studijskiProgrami;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public KatedraDTO getKatedra() {
        return katedra;
    }

    public void setKatedra(KatedraDTO katedra) {
        this.katedra = katedra;
    }

    public List<StudijskiProgramDTO> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(List<StudijskiProgramDTO> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
