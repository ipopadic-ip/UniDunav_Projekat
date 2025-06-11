package com.unidunav.dokument.dto;

import java.time.LocalDateTime;

public class VerzijaDokumentaDTO {
    private Long id;
    private int brojVerzije;
    private String autor;
    private LocalDateTime datumKreiranja;
    private String putanjaDoFajla;
    private String sadrzaj;
    private boolean deleted;
    private DokumentDTO dokument;

    public DokumentDTO getDokument() {
        return dokument;
    }

    public void setDokument(DokumentDTO dokument) {
        this.dokument = dokument;
    }

    
    
    
    
    
    
	
	public VerzijaDokumentaDTO(Long id, int brojVerzije, String autor, LocalDateTime datumKreiranja,
			String putanjaDoFajla, String sadrzaj, boolean deleted) {
		super();
		this.id = id;
		this.brojVerzije = brojVerzije;
		this.autor = autor;
		this.datumKreiranja = datumKreiranja;
		this.putanjaDoFajla = putanjaDoFajla;
		this.sadrzaj = sadrzaj;
		this.deleted = deleted;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public VerzijaDokumentaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public VerzijaDokumentaDTO(Long id, int brojVerzije, String autor, LocalDateTime datumKreiranja,
//			String putanjaDoFajla, String sadrzaj) {
//		super();
//		this.id = id;
//		this.brojVerzije = brojVerzije;
//		this.autor = autor;
//		this.datumKreiranja = datumKreiranja;
//		this.putanjaDoFajla = putanjaDoFajla;
//		this.sadrzaj = sadrzaj;
//	}
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
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
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
    
    
}
