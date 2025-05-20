package com.unidunav.sluzbenik.model;

import com.unidunav.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Potvrda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // npr. “Uverenje o statusu studenta”, “Potvrda o redovnom studiranju” …
    @Column(nullable = false)
    private String tip;

    // Dodatni opis ili tekst potvrde (max 1000)
    @Column(nullable = false, length = 1000)
    private String tekst;

    @Column(nullable = false)
    private LocalDate datumIzdavanja;

    // Student kome se izdaje potvrda
    @ManyToOne(optional = false)
    private User student;

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

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

    // ===== getters / setters =====
    
}
