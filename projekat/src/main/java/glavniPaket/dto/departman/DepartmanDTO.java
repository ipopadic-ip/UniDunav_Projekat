package glavniPaket.dto.departman;

import java.util.ArrayList;

import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;

public class DepartmanDTO {
    private Long id;
    private String naziv;
    private String opis;
    private FakultetDTO fakultet;
    private ProfesorDTO sefDepartmana;
    private ArrayList<KatedraDTO> katedre = new ArrayList<>();

    public DepartmanDTO() {}

    public DepartmanDTO(Long id, String naziv, String opis,
                        FakultetDTO fakultet, ProfesorDTO sefDepartmana,
                        ArrayList<KatedraDTO> katedre) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.fakultet = fakultet;
        this.sefDepartmana = sefDepartmana;
        this.katedre = katedre;
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

    public FakultetDTO getFakultet() {
        return fakultet;
    }

    public void setFakultet(FakultetDTO fakultet) {
        this.fakultet = fakultet;
    }

    public ProfesorDTO getSefDepartmana() {
        return sefDepartmana;
    }

    public void setSefDepartmana(ProfesorDTO sefDepartmana) {
        this.sefDepartmana = sefDepartmana;
    }

    public ArrayList<KatedraDTO> getKatedre() {
        return katedre;
    }

    public void setKatedre(ArrayList<KatedraDTO> katedre) {
        this.katedre = katedre;
    }
}
