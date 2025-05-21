package com.unidunav.profesorPredmet.dto;

public class IshodDTO {
    private String tema;

    public IshodDTO() {}

    public IshodDTO(String tema) {
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
