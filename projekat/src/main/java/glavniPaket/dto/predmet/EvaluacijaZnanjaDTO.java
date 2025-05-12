package glavniPaket.dto.predmet;

import java.time.LocalDateTime;

public class EvaluacijaZnanjaDTO {
    private Long id;
    private LocalDateTime vremePocetka;
    private int brojBodova;
    private TipEvaluacijeDTO tipEvaluacije;
    private PohadjanjePredmetaDTO pohadjanje;

    public EvaluacijaZnanjaDTO() {}

    public EvaluacijaZnanjaDTO(Long id, LocalDateTime vremePocetka, int brojBodova,
                                TipEvaluacijeDTO tipEvaluacije, PohadjanjePredmetaDTO pohadjanje) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.brojBodova = brojBodova;
        this.tipEvaluacije = tipEvaluacije;
        this.pohadjanje = pohadjanje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public TipEvaluacijeDTO getTipEvaluacije() {
        return tipEvaluacije;
    }

    public void setTipEvaluacije(TipEvaluacijeDTO tipEvaluacije) {
        this.tipEvaluacije = tipEvaluacije;
    }

    public PohadjanjePredmetaDTO getPohadjanje() {
        return pohadjanje;
    }

    public void setPohadjanje(PohadjanjePredmetaDTO pohadjanje) {
        this.pohadjanje = pohadjanje;
    }
}

