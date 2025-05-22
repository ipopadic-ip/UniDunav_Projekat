package com.unidunav.predmet.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prijave_prestupa")
public class PrijavaPrestupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String opis;

    @Column(nullable = false)
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "pohadjanje_id", nullable = false)
    private PohadjanjePredmeta pohadjanje;

    public PrijavaPrestupa() {}

    public PrijavaPrestupa(Long id, String opis, LocalDateTime datum, PohadjanjePredmeta pohadjanje) {
        this.id = id;
        this.opis = opis;
        this.datum = datum;
        this.pohadjanje = pohadjanje;
    }

    // Getteri i setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public PohadjanjePredmeta getPohadjanje() {
        return pohadjanje;
    }

    public void setPohadjanje(PohadjanjePredmeta pohadjanje) {
        this.pohadjanje = pohadjanje;
    }
}
