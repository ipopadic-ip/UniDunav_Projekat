package com.unidunav.sluzbenik.dto;

public class KnjigaDTO {
    private Long id;
    private String naziv;
    private String autor;
    private int brojDostupnihPrimeraka;
    private Long primerakId; 

    public KnjigaDTO() {
        super();
    }

    public KnjigaDTO(Long id, String naziv, String autor, int brojDostupnihPrimeraka, Long primerakId) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.brojDostupnihPrimeraka = brojDostupnihPrimeraka;
        this.primerakId = primerakId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getBrojDostupnihPrimeraka() {
        return brojDostupnihPrimeraka;
    }

    public void setBrojDostupnihPrimeraka(int brojDostupnihPrimeraka) {
        this.brojDostupnihPrimeraka = brojDostupnihPrimeraka;
    }

    public Long getPrimerakId() {
        return primerakId;
    }

    public void setPrimerakId(Long primerakId) {
        this.primerakId = primerakId;
    }
}