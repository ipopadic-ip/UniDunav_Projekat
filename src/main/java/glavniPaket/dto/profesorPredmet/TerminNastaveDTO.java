package glavniPaket.dto.profesorPredmet;

import java.time.LocalDateTime;

import glavniPaket.model.profesorPredmet.TerminNastave;

public class TerminNastaveDTO {
    private Long id;
    private LocalDateTime terminPocetka;
    private LocalDateTime terminZavrsetka;
    private ProfesorPredmetDTO profesorPredmet;
    private IshodDTO ishod;

    public TerminNastaveDTO() {}

    public TerminNastaveDTO(Long id, LocalDateTime terminPocetka, LocalDateTime terminZavrsetka,
                            ProfesorPredmetDTO profesorPredmet, IshodDTO ishod) {
        this.id = id;
        this.terminPocetka = terminPocetka;
        this.terminZavrsetka = terminZavrsetka;
        this.profesorPredmet = profesorPredmet;
        this.ishod = ishod;
    }
    
    public TerminNastaveDTO(TerminNastave terminNastave) {
        this.id = terminNastave.getId();
        this.terminPocetka = terminNastave.getTerminPocetka();
        this.terminZavrsetka = terminNastave.getTerminZavrsetka();
        this.profesorPredmet = terminNastave.getProfesorPredmet() != null 
            ? new ProfesorPredmetDTO(terminNastave.getProfesorPredmet()) : null;
        this.ishod = terminNastave.getIshod() != null 
            ? new IshodDTO(terminNastave.getIshod()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTerminPocetka() {
        return terminPocetka;
    }

    public void setTerminPocetka(LocalDateTime terminPocetka) {
        this.terminPocetka = terminPocetka;
    }

    public LocalDateTime getTerminZavrsetka() {
        return terminZavrsetka;
    }

    public void setTerminZavrsetka(LocalDateTime terminZavrsetka) {
        this.terminZavrsetka = terminZavrsetka;
    }

    public ProfesorPredmetDTO getProfesorPredmet() {
        return profesorPredmet;
    }

    public void setProfesorPredmet(ProfesorPredmetDTO profesorPredmet) {
        this.profesorPredmet = profesorPredmet;
    }

    public IshodDTO getIshod() {
        return ishod;
    }

    public void setIshod(IshodDTO ishod) {
        this.ishod = ishod;
    }
    
    public TerminNastave toEntity() {
        TerminNastave terminNastave = new TerminNastave();
        terminNastave.setId(this.id);
        terminNastave.setTerminPocetka(this.terminPocetka);
        terminNastave.setTerminZavrsetka(this.terminZavrsetka);
        terminNastave.setProfesorPredmet(this.profesorPredmet != null ? this.profesorPredmet.toEntity() : null);
        terminNastave.setIshod(this.ishod != null ? this.ishod.toEntity() : null);
        return terminNastave;
    }
}
