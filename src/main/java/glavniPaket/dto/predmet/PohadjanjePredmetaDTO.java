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

    private Long studentId;
    private Long predmetId;

    private List<Long> prijavePrestupaIds;
    private List<Long> prijaveIspitaIds;
    private List<Long> evaluacijeIds;

    public PohadjanjePredmetaDTO() {}

    public PohadjanjePredmetaDTO(Long id, int ocena, int brojPolaganja, boolean aktivan,
                                  LocalDateTime datumPocetka, LocalDateTime datumZavrsetka,
                                  Long studentId, Long predmetId,
                                  List<Long> prijavePrestupaIds,
                                  List<Long> prijaveIspitaIds,
                                  List<Long> evaluacijeIds) {
        this.id = id;
        this.ocena = ocena;
        this.brojPolaganja = brojPolaganja;
        this.aktivan = aktivan;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.studentId = studentId;
        this.predmetId = predmetId;
        this.prijavePrestupaIds = prijavePrestupaIds;
        this.prijaveIspitaIds = prijaveIspitaIds;
        this.evaluacijeIds = evaluacijeIds;
    }

    // Getteri i setteri

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getOcena() { return ocena; }
    public void setOcena(int ocena) { this.ocena = ocena; }

    public int getBrojPolaganja() { return brojPolaganja; }
    public void setBrojPolaganja(int brojPolaganja) { this.brojPolaganja = brojPolaganja; }

    public boolean isAktivan() { return aktivan; }
    public void setAktivan(boolean aktivan) { this.aktivan = aktivan; }

    public LocalDateTime getDatumPocetka() { return datumPocetka; }
    public void setDatumPocetka(LocalDateTime datumPocetka) { this.datumPocetka = datumPocetka; }

    public LocalDateTime getDatumZavrsetka() { return datumZavrsetka; }
    public void setDatumZavrsetka(LocalDateTime datumZavrsetka) { this.datumZavrsetka = datumZavrsetka; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

    public List<Long> getPrijavePrestupaIds() { return prijavePrestupaIds; }
    public void setPrijavePrestupaIds(List<Long> prijavePrestupaIds) { this.prijavePrestupaIds = prijavePrestupaIds; }

    public List<Long> getPrijaveIspitaIds() { return prijaveIspitaIds; }
    public void setPrijaveIspitaIds(List<Long> prijaveIspitaIds) { this.prijaveIspitaIds = prijaveIspitaIds; }

    public List<Long> getEvaluacijeIds() { return evaluacijeIds; }
    public void setEvaluacijeIds(List<Long> evaluacijeIds) { this.evaluacijeIds = evaluacijeIds; }
}