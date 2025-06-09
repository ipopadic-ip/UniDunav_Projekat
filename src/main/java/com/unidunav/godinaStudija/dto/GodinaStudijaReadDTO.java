package com.unidunav.godinaStudija.dto;

import jakarta.persistence.Column;

public class GodinaStudijaReadDTO {
		private Long id;
	    private int godina;
	    private Long studijskiProgramId;
	    private String studijskiProgramNaziv;
	    private boolean deleted;
		public GodinaStudijaReadDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public GodinaStudijaReadDTO(Long id, int godina, Long studijskiProgramId, String studijskiProgramNaziv, boolean deleted) {
			super();
			this.id = id;
			this.godina = godina;
			this.studijskiProgramId = studijskiProgramId;
			this.studijskiProgramNaziv = studijskiProgramNaziv;
			this.deleted = deleted;
		}
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
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
		public Long getStudijskiProgramId() {
			return studijskiProgramId;
		}
		public void setStudijskiProgramId(Long studijskiProgramId) {
			this.studijskiProgramId = studijskiProgramId;
		}
		public String getStudijskiProgramNaziv() {
			return studijskiProgramNaziv;
		}
		public void setStudijskiProgramNaziv(String studijskiProgramNaziv) {
			this.studijskiProgramNaziv = studijskiProgramNaziv;
		}
	    
	    
}
