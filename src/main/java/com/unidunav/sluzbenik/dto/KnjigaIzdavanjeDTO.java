package com.unidunav.sluzbenik.dto;

public class KnjigaIzdavanjeDTO {
    private Long knjigaId;
    private String naziv;
    private String autor;
    private boolean dostupna;      // postoji makar jedan dostupan primerak
    private boolean vecIznajmljena; // student je već iznajmio ovu knjigu
    private Long primerakId;

    public Long getPrimerakId() {
        return primerakId;
    }

    public void setPrimerakId(Long primerakId) {
        this.primerakId = primerakId;
    }

   
	public KnjigaIzdavanjeDTO(Long knjigaId, String naziv, String autor, boolean dostupna, boolean vecIznajmljena,
			Long primerakId) {
		super();
		this.knjigaId = knjigaId;
		this.naziv = naziv;
		this.autor = autor;
		this.dostupna = dostupna;
		this.vecIznajmljena = vecIznajmljena;
		this.primerakId = primerakId;
	}

	public Long getKnjigaId() {
		return knjigaId;
	}

	public void setKnjigaId(Long knjigaId) {
		this.knjigaId = knjigaId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isDostupna() {
		return dostupna;
	}

	public void setDostupna(boolean dostupna) {
		this.dostupna = dostupna;
	}

	public boolean isVecIznajmljena() {
		return vecIznajmljena;
	}

	public void setVecIznajmljena(boolean vecIznajmljena) {
		this.vecIznajmljena = vecIznajmljena;
	}

    
}

