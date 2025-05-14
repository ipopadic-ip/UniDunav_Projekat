package glavniPaket.dto.departman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.model.departman.Departman;

public class DepartmanDTO {
    private Long id;
    private String naziv;
    private String opis;
    private FakultetDTO fakultet;
    private ProfesorDTO sefDepartmana;
    private List<KatedraDTO> katedre = new ArrayList<>();

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
    
    public DepartmanDTO(Departman departman) {
        this.id = departman.getId();
        this.naziv = departman.getNaziv();
        this.opis = departman.getOpis();
        this.fakultet = departman.getFakultet() != null ? new FakultetDTO(departman.getFakultet()) : null;
        this.sefDepartmana = departman.getSefDepartmana() != null ? new ProfesorDTO(departman.getSefDepartmana()) : null;
        this.katedre = departman.getKatedre() != null
                ? departman.getKatedre().stream().map(KatedraDTO::new).collect(Collectors.toList())
                : new ArrayList<>();
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

    public List<KatedraDTO> getKatedre() {
        return katedre;
    }

    public void setKatedre(ArrayList<KatedraDTO> katedre) {
        this.katedre = katedre;
    }
    
    public Departman toEntity() {
        Departman departman = new Departman();
        departman.setId(this.id);
        departman.setNaziv(this.naziv);
        departman.setOpis(this.opis);

        if (this.fakultet != null) {
            departman.setFakultet(this.fakultet.toEntity());
        }

        if (this.sefDepartmana != null) {
            departman.setSefDepartmana(this.sefDepartmana.toEntity());
        }

        if (this.katedre != null) {
            departman.setKatedre(
                this.katedre.stream()
                    .map(KatedraDTO::toEntity)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return departman;
    }

}
