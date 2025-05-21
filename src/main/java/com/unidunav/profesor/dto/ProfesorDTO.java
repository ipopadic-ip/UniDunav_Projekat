package com.unidunav.profesor.dto;

public class ProfesorDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String biografija;
    
    private String slikaPath;

    public String getSlikaPath() {
        return slikaPath;
    }

    public void setSlikaPath(String slikaPath) {
        this.slikaPath = slikaPath;
    }

    

    public ProfesorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesorDTO(Long id, String ime, String prezime, String biografija) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.biografija = biografija;
	}

	// Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }
}
