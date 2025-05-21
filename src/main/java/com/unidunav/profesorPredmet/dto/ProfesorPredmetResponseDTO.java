package com.unidunav.profesorPredmet.dto;

public class ProfesorPredmetResponseDTO {
	 	private Long id;
	    private Long profesorId;
	    private String profesorIme;
	    private Long predmetId;
	    private String predmetNaziv;

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public Long getProfesorId() { return profesorId; }
	    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }

	    public String getProfesorIme() { return profesorIme; }
	    public void setProfesorIme(String profesorIme) { this.profesorIme = profesorIme; }

	    public Long getPredmetId() { return predmetId; }
	    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

	    public String getPredmetNaziv() { return predmetNaziv; }
	    public void setPredmetNaziv(String predmetNaziv) { this.predmetNaziv = predmetNaziv; }
}
