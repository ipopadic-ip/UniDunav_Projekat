package glavniPaket.dto.predmet;

import java.time.LocalDate;

public class ObavestenjeDTO {

    private Long id;
    private String tekst;
    private LocalDate datum;
    private Long predmetId;

    public ObavestenjeDTO() {}

    public ObavestenjeDTO(Long id, String tekst, LocalDate datum, Long predmetId) {
        this.id = id;
        this.tekst = tekst;
        this.datum = datum;
        this.predmetId = predmetId;
    }

    // === GETTERI I SETTERI ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }
}