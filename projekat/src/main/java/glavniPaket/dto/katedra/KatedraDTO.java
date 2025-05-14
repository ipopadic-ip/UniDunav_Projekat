package glavniPaket.dto.katedra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.katedra.Katedra;

import java.util.ArrayList;

public class KatedraDTO {
    private Long id;
    private String naziv;
    private String opis;
    private DepartmanDTO departman;
    private ProfesorDTO sefKatedre;
    private ArrayList<PredmetDTO> predmeti = new ArrayList<>();
    private List<TipStudijaDTO> tipoviStudija = new ArrayList<>();

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
    
    public KatedraDTO(Katedra katedra) {
        if (katedra != null) {
            this.id = katedra.getId();
            this.naziv = katedra.getNaziv();
            this.opis = katedra.getOpis();
        }
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

    public List<TipStudijaDTO> getTipoviStudija() {
        return tipoviStudija;
    }

    public void setTipoviStudija(ArrayList<TipStudijaDTO> tipoviStudija) {
        this.tipoviStudija = tipoviStudija;
    }
    
    public Katedra toEntity() {
        Katedra katedra = new Katedra();
        katedra.setId(this.id);
        katedra.setNaziv(this.naziv);
        katedra.setOpis(this.opis);

        if (this.departman != null) {
            katedra.setDepartman(this.departman.toEntity());
        }

        if (this.sefKatedre != null) {
            katedra.setSefKatedre(this.sefKatedre.toEntity());
        }

        if (this.tipoviStudija != null) {
            katedra.setTipoviStudija(
                this.tipoviStudija.stream()
                    .map(TipStudijaDTO::toEntity)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return katedra;
    }

    
}
