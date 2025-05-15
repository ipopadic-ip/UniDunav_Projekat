package glavniPaket.dto.predmet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import glavniPaket.dto.korisnika.StudentDTO;

public class PohadjanjePredmetaDTO {
    private Long id;
    private int ocena;
    private int brojPolaganja;
    private boolean aktivan;
    private LocalDateTime datumPocetka;
    private LocalDateTime datumZavrsetka;
    private StudentDTO student;
    private PredmetDTO predmet;
    private List<PrijavaPrestupaDTO> prijave = new ArrayList<>();
    private List<EvaluacijaZnanjaDTO> evaluacije = new ArrayList<>();

    public PohadjanjePredmetaDTO() {}

    public PohadjanjePredmetaDTO(Long id, int ocena, int brojPolaganja, boolean aktivan,
                                  LocalDateTime datumPocetka, LocalDateTime datumZavrsetka,
                                  StudentDTO student, PredmetDTO predmet,
                                  List<PrijavaPrestupaDTO> prijave, List<EvaluacijaZnanjaDTO> evaluacije) {
        this.id = id;
        this.ocena = ocena;
        this.brojPolaganja = brojPolaganja;
        this.aktivan = aktivan;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.student = student;
        this.predmet = predmet;
        this.prijave = prijave;
        this.evaluacije = evaluacije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getBrojPolaganja() {
        return brojPolaganja;
    }

    public void setBrojPolaganja(int brojPolaganja) {
        this.brojPolaganja = brojPolaganja;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public LocalDateTime getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDateTime datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDateTime getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(LocalDateTime datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public List<PrijavaPrestupaDTO> getPrijave() {
        return prijave;
    }

    public void setPrijave(List<PrijavaPrestupaDTO> prijave) {
        this.prijave = prijave;
    }

    public List<EvaluacijaZnanjaDTO> getEvaluacije() {
        return evaluacije;
    }

    public void setEvaluacije(List<EvaluacijaZnanjaDTO> evaluacije) {
        this.evaluacije = evaluacije;
    }
}

