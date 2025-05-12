package glavniPaket.dto.korisnika;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;
import glavniPaket.model.predmet.PohadjanjePredmeta;

public class StudentDTO {
    private Integer id;
    private RegistrovaniKorisnikDTO korisnik;
    private Date godinaUpisa;
    private AdresaDTO adresa;
    private String brojIndeksa;
    private int ukupanBrojECTS;
    private List<PohadjanjePredmetaDTO> pohadjanja = new ArrayList<>();

    public StudentDTO() {}

    public StudentDTO(Integer id, RegistrovaniKorisnikDTO korisnik, Date godinaUpisa, AdresaDTO adresa,
                      String brojIndeksa, int ukupanBrojECTS, List<PohadjanjePredmetaDTO> pohadjanja) {
        this.id = id;
        this.korisnik = korisnik;
        this.godinaUpisa = godinaUpisa;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
        this.ukupanBrojECTS = ukupanBrojECTS;
        this.pohadjanja = pohadjanja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegistrovaniKorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public Date getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(Date godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
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

    public List<PohadjanjePredmetaDTO> getPohadjanja() {
        return pohadjanja;
    }

    public void setPohadjanja(List<PohadjanjePredmetaDTO> pohadjanja) {
        this.pohadjanja = pohadjanja;
    }
}