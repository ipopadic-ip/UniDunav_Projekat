package com.unidunav.profesorPredmet.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.unidunav.user.model.User;

@Entity
@Table(name = "termin_nastave")
public class TerminNastave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime terminPocetka;

    private LocalDateTime terminZavrsetka;
    
    

    @ManyToOne(optional = false)
    @JoinColumn(name = "profesor_predmet_id", nullable = false)
    private ProfesorPredmet profesorPredmet;

    private String ishod;

	public TerminNastave() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	



	






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

	public ProfesorPredmet getProfesorPredmet() {
		return profesorPredmet;
	}

	public void setProfesorPredmet(ProfesorPredmet profesorPredmet) {
		this.profesorPredmet = profesorPredmet;
	}














	public TerminNastave(Long id, LocalDateTime terminPocetka, LocalDateTime terminZavrsetka,
			ProfesorPredmet profesorPredmet, String ishod) {
		super();
		this.id = id;
		this.terminPocetka = terminPocetka;
		this.terminZavrsetka = terminZavrsetka;
		this.profesorPredmet = profesorPredmet;
		this.ishod = ishod;
	}














	public String getIshod() {
		return ishod;
	}














	public void setIshod(String ishod) {
		this.ishod = ishod;
	}

	
    
}
