package glavniPaket.model.adresa;

import glavniPaket.dto.adresa.AdresaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ulica;
    private String broj;

    @ManyToOne
    @JoinColumn(name = "mesto_id", nullable = false)
    private Mesto mesto;

    public Adresa() {}

    public Adresa(Long id, String ulica, String broj, Mesto mesto) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    // Metoda za konverziju iz Adresa entiteta u AdresaDTO
    public AdresaDTO toDTO() {
        return new AdresaDTO(this.id, this.broj, this.ulica, this.mesto != null ? this.mesto.toDTO() : null);
    }
}