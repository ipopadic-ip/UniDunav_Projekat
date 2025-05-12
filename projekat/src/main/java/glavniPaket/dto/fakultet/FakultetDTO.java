package glavniPaket.dto.fakultet;

import java.util.ArrayList;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;

public class FakultetDTO {
    private Long id;
    private String naziv;
    private String email;
    private String opis;
    private UniverzitetDTO univerzitet;
    private ProfesorDTO dekan;
    private ArrayList<DepartmanDTO> departmani = new ArrayList<>();

    public FakultetDTO() {}

    public FakultetDTO(Long id, String naziv, String email, String opis,
                       UniverzitetDTO univerzitet, ProfesorDTO dekan,
                       ArrayList<DepartmanDTO> departmani) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.opis = opis;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.departmani = departmani;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public UniverzitetDTO getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(UniverzitetDTO univerzitet) {
        this.univerzitet = univerzitet;
    }

    public ProfesorDTO getDekan() {
        return dekan;
    }

    public void setDekan(ProfesorDTO dekan) {
        this.dekan = dekan;
    }

    public ArrayList<DepartmanDTO> getDepartmani() {
        return departmani;
    }

    public void setDepartmani(ArrayList<DepartmanDTO> departmani) {
        this.departmani = departmani;
    }
}