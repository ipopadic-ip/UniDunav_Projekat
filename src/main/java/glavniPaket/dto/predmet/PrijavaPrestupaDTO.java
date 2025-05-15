package glavniPaket.dto.predmet;

import java.time.LocalDateTime;

public class PrijavaPrestupaDTO {
    private Long id;
    private String opis;
    private LocalDateTime datum;
    private PohadjanjePredmetaDTO pohadjanje;

    public PrijavaPrestupaDTO() {}

    public PrijavaPrestupaDTO(Long id, String opis, LocalDateTime datum, PohadjanjePredmetaDTO pohadjanje) {
        this.id = id;
        this.opis = opis;
        this.datum = datum;
        this.pohadjanje = pohadjanje;
    }

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

    public PohadjanjePredmetaDTO getPohadjanje() {
        return pohadjanje;
    }

    public void setPohadjanje(PohadjanjePredmetaDTO pohadjanje) {
        this.pohadjanje = pohadjanje;
    }
}