package com.unidunav.sluzbenik.dto;

public class IzdavanjeKnjigeRequestDTO {
    private Long primerakId;
    private String brojIndeksa;

    public Long getPrimerakId() {
        return primerakId;
    }

    public void setPrimerakId(Long primerakId) {
        this.primerakId = primerakId;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }
}