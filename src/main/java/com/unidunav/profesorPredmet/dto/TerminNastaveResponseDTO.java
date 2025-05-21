package com.unidunav.profesorPredmet.dto;

import java.time.LocalDateTime;

public class TerminNastaveResponseDTO {
	 	private Long id;
	    private LocalDateTime terminPocetka;
	    private LocalDateTime terminZavrsetka;
	    private String autorIme;
	    private Long profesorPredmetId;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		public String getAutorIme() {
			return autorIme;
		}
		public void setAutorIme(String autorIme) {
			this.autorIme = autorIme;
		}
		public Long getProfesorPredmetId() {
			return profesorPredmetId;
		}
		public void setProfesorPredmetId(Long profesorPredmetId) {
			this.profesorPredmetId = profesorPredmetId;
		}
	    
}
