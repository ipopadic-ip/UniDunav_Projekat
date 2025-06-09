package com.unidunav.studijskiProgram.dto;

import java.util.List;

public class GrupisaniProgramiDTO {
	 private String tipStudija;
	 private List<StudijskiProgramDTO> programi;

	    public GrupisaniProgramiDTO(String tipStudija, List<StudijskiProgramDTO> programi) {
	        this.tipStudija = tipStudija;
	        this.programi = programi;
	    }

	    public String getTipStudija() {
	        return tipStudija;
	    }

	    public void setTipStudija(String tipStudija) {
	        this.tipStudija = tipStudija;
	    }

	    public List<StudijskiProgramDTO> getProgrami() {
	        return programi;
	    }

	    public void setProgrami(List<StudijskiProgramDTO> programi) {
	        this.programi = programi;
	    }
}
