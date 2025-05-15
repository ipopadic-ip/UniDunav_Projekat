package glavniPaket.dto.predmet;

import java.util.ArrayList;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.profesorPredmet.ProfesorPredmetDTO;
import glavniPaket.model.predmet.Predmet;

public class PredmetDTO {
    private Long id;
    private String naziv;
    private int ests;
    private String informacijeOPredmetu;
    private GodinaStudijaDTO godinaStudija;

    public PredmetDTO() {}

    public PredmetDTO(Long id, String naziv, int ests, String informacijeOPredmetu, GodinaStudijaDTO godinaStudija) {
        this.id = id;
        this.naziv = naziv;
        this.ests = ests;
        this.informacijeOPredmetu = informacijeOPredmetu;
        this.godinaStudija = godinaStudija;
    }
    
    public PredmetDTO(Predmet predmet) {
        this.id = predmet.getId();
        this.naziv = predmet.getNaziv();
        this.ests = predmet.getEsts();
        this.informacijeOPredmetu = predmet.getInformacijeOPredmetu();
        this.godinaStudija = predmet.getGodinaStudija() != null ? new GodinaStudijaDTO(predmet.getGodinaStudija()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEsts() {
        return ests;
    }

    public void setEsts(int ests) {
        this.ests = ests;
    }

    public String getInformacijeOPredmetu() {
        return informacijeOPredmetu;
    }

    public void setInformacijeOPredmetu(String informacijeOPredmetu) {
        this.informacijeOPredmetu = informacijeOPredmetu;
    }

    public GodinaStudijaDTO getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
        this.godinaStudija = godinaStudija;
    }
    
    public Predmet toEntity() {
        Predmet predmet = new Predmet();
        predmet.setId(this.id);
        predmet.setNaziv(this.naziv);
        predmet.setEsts(this.ests);
        predmet.setInformacijeOPredmetu(this.informacijeOPredmetu);
        
        // Convert GodinaStudijaDTO to GodinaStudija
        if (this.godinaStudija != null) {
            predmet.setGodinaStudija(this.godinaStudija.toEntity());  // Assuming GodinaStudijaDTO has a toEntity() method
        }
        
        return predmet;
    }

}