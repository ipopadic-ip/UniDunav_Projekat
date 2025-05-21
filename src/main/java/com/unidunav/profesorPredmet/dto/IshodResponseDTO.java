package com.unidunav.profesorPredmet.dto;

public class IshodResponseDTO {
	 	private Long id;
	    private String tema;

	    public IshodResponseDTO() {}

	    public IshodResponseDTO(Long id, String tema) {
	        this.id = id;
	        this.tema = tema;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTema() {
	        return tema;
	    }

	    public void setTema(String tema) {
	        this.tema = tema;
	    }
}
