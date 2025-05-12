package glavniPaket.dto.katedra;

import java.util.ArrayList;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;

import java.util.ArrayList;

public class KatedraDTO {
    private Long id;
    private String naziv;
    private String opis;
    private DepartmanDTO departman;
    private ProfesorDTO sefKatedre;
    private ArrayList<PredmetDTO> predmeti = new ArrayList<>();
    private ArrayList<TipStudijaDTO> tipoviStudija = new ArrayList<>();

    public KatedraDTO() {}

    public KatedraDTO(Long id, String naziv, String opis, DepartmanDTO departman,
                      ProfesorDTO sefKatedre, ArrayList<PredmetDTO> predmeti,
                      ArrayList<TipStudijaDTO> tipoviStudija) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.departman = departman;
        this.sefKatedre = sefKatedre;
        this.predmeti = predmeti;
        this.tipoviStudija = tipoviStudija;
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

    public DepartmanDTO getDepartman() {
        return departman;
    }

    public void setDepartman(DepartmanDTO departman) {
        this.departman = departman;
    }

    public ProfesorDTO getSefKatedre() {
        return sefKatedre;
    }

    public void setSefKatedre(ProfesorDTO sefKatedre) {
        this.sefKatedre = sefKatedre;
    }

    public ArrayList<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(ArrayList<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public ArrayList<TipStudijaDTO> getTipoviStudija() {
        return tipoviStudija;
    }

    public void setTipoviStudija(ArrayList<TipStudijaDTO> tipoviStudija) {
        this.tipoviStudija = tipoviStudija;
    }
}
