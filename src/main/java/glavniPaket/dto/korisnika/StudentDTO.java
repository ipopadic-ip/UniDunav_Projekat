package glavniPaket.dto.korisnika;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;
import glavniPaket.model.predmet.PohadjanjePredmeta;
import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;

public class StudentDTO {

    private Long id;
    private Long korisnikId;
    private LocalDate godinaUpisa;
    private Long adresaId;
    private String brojIndeksa;
    private int ukupanBrojECTS;
    private List<Long> pohadjanjeIds;

    public StudentDTO() {
    }

    public StudentDTO(Long id, Long korisnikId, LocalDate godinaUpisa, Long adresaId,
                      String brojIndeksa, int ukupanBrojECTS, List<Long> pohadjanjeIds) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.godinaUpisa = godinaUpisa;
        this.adresaId = adresaId;
        this.brojIndeksa = brojIndeksa;
        this.ukupanBrojECTS = ukupanBrojECTS;
        this.pohadjanjeIds = pohadjanjeIds;
    }

    // === GETTERI I SETTERI ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public LocalDate getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(LocalDate godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public Long getAdresaId() {
        return adresaId;
    }

    public void setAdresaId(Long adresaId) {
        this.adresaId = adresaId;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public int getUkupanBrojECTS() {
        return ukupanBrojECTS;
    }

    public void setUkupanBrojECTS(int ukupanBrojECTS) {
        this.ukupanBrojECTS = ukupanBrojECTS;
    }

    public List<Long> getPohadjanjeIds() {
        return pohadjanjeIds;
    }

    public void setPohadjanjeIds(List<Long> pohadjanjeIds) {
        this.pohadjanjeIds = pohadjanjeIds;
    }
}
