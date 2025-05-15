package glavniPaket.dto.studijskiProgram;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.model.tipStudija.TipStudija;

public class StudijskiProgramDTO {
    private Long id;
    private String naziv;
    private String opis;
    private GodinaStudijaDTO godinaStudija;
    private TipStudijaDTO tipStudija;
    private ProfesorDTO rukovodilac;

    public StudijskiProgramDTO() {
        super();
    }

    public StudijskiProgramDTO(Long id, String naziv, String opis, GodinaStudijaDTO godinaStudija, TipStudijaDTO tipStudija, ProfesorDTO rukovodilac) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.godinaStudija = godinaStudija;
        this.tipStudija = tipStudija;
        this.rukovodilac = rukovodilac;
    }

    public StudijskiProgramDTO(StudijskiProgram studijskiProgram) {
        this.id = studijskiProgram.getId();
        this.naziv = studijskiProgram.getNaziv();
        this.opis = studijskiProgram.getOpis();
        this.godinaStudija = studijskiProgram.getGodinaStudija() != null 
            ? new GodinaStudijaDTO(studijskiProgram.getGodinaStudija()) : null;
        this.tipStudija = studijskiProgram.getTipStudija() != null 
            ? new TipStudijaDTO(studijskiProgram.getTipStudija()) : null;
        this.rukovodilac = studijskiProgram.getRukovodilac() != null 
            ? new ProfesorDTO(studijskiProgram.getRukovodilac()) : null;
    }

    public StudijskiProgram toEntity() {
        StudijskiProgram sp = new StudijskiProgram();
        sp.setId(this.id);
        sp.setNaziv(this.naziv);
        sp.setOpis(this.opis);

        sp.setTipStudija(this.tipStudija != null ? this.tipStudija.toEntity() : null);
        
        sp.setRukovodilac(this.rukovodilac != null ? this.rukovodilac.toEntity() : null);
        
        return sp;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public GodinaStudijaDTO getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public TipStudijaDTO getTipStudija() {
        return tipStudija;
    }

    public void setTipStudija(TipStudijaDTO tipStudija) {
        this.tipStudija = tipStudija;
    }

    public ProfesorDTO getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(ProfesorDTO rukovodilac) {
        this.rukovodilac = rukovodilac;
    }
}
