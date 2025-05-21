package com.unidunav.profesorPredmet.dto;

import java.time.LocalDateTime;

public class TerminNastaveDTO {
	 	private LocalDateTime terminPocetka;
	    private LocalDateTime terminZavrsetka;
	    private Long profesorPredmetId;
		public LocalDateTime getTerminPocetka() {
			return terminPocetka;
		}
		public void setTerminPocetka(LocalDateTime terminPocetka) {
			this.terminPocetka = terminPocetka;
		}
		public LocalDateTime getTerminZavrsetka() {
			return terminZavrsetka;
		}
		public void setTerminZavrsetka(LocalDateTime terminZavrsetka) {
			this.terminZavrsetka = terminZavrsetka;
		}
		public Long getProfesorPredmetId() {
			return profesorPredmetId;
		}
		public void setProfesorPredmetId(Long profesorPredmetId) {
			this.profesorPredmetId = profesorPredmetId;
		}
	    
}
