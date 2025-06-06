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
    
    @ManyToOne
    private User autor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profesor_predmet_id", nullable = false)
    private ProfesorPredmet profesorPredmet;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ishod_id")
    private Ishod ishod;

	public TerminNastave() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User getAutor() {
		return autor;
	}



	public void setAutor(User autor) {
		this.autor = autor;
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

	public Ishod getIshod() {
		return ishod;
	}

	public void setIshod(Ishod ishod) {
		this.ishod = ishod;
	}
    
}
