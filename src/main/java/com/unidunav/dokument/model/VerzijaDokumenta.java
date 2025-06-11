package com.unidunav.dokument.model;

import java.time.LocalDateTime;

import com.unidunav.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class VerzijaDokumenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int brojVerzije;

    private LocalDateTime datumKreiranja;

    private String putanjaDoFajla;

    @Lob
    private String sadrzaj; // ako je tekstualni sadržaj
    
    @Column(nullable = false)
	private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "dokument_id")
    private Dokument dokument;
    
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojVerzije() {
		return brojVerzije;
	}

	public void setBrojVerzije(int brojVerzije) {
		this.brojVerzije = brojVerzije;
	}

	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

	public LocalDateTime getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(LocalDateTime datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public String getPutanjaDoFajla() {
		return putanjaDoFajla;
	}

	public void setPutanjaDoFajla(String putanjaDoFajla) {
		this.putanjaDoFajla = putanjaDoFajla;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Dokument getDokument() {
		return dokument;
	}

	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}

	

	

	public VerzijaDokumenta(Long id, int brojVerzije, LocalDateTime datumKreiranja, String putanjaDoFajla,
			String sadrzaj, boolean deleted, Dokument dokument, User autor) {
		super();
		this.id = id;
		this.brojVerzije = brojVerzije;
		this.datumKreiranja = datumKreiranja;
		this.putanjaDoFajla = putanjaDoFajla;
		this.sadrzaj = sadrzaj;
		this.deleted = deleted;
		this.dokument = dokument;
		this.autor = autor;
	}

	public VerzijaDokumenta() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

