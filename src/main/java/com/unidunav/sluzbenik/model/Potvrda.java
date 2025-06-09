package com.unidunav.sluzbenik.model;

import com.unidunav.user.model.User;
import com.unidunav.student.model.Student;
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
    private Student student;

    @PrePersist
    public void prePersist() {
        if (datumIzdavanja == null) {
            datumIzdavanja = LocalDate.now();
        }
    }
//    private User student;
    
    public Potvrda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Potvrda(Long id, String tip, String tekst, LocalDate datumIzdavanja, Student student) {
		super();
		this.id = id;
		this.tip = tip;
		this.tekst = tekst;
		this.datumIzdavanja = datumIzdavanja;
		this.student = student;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

//	public User getStudent() {
//		return student;
//	}
//
//	public void setStudent(User student) {
//		this.student = student;
//	}

    
}
