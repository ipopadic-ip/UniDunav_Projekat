package glavniPaket.dto.fakultet;

import java.util.ArrayList;
import java.util.stream.Collectors;

import glavniPaket.dto.departman.DepartmanDTO;
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
    
    public FakultetDTO(Fakultet fakultet) {
        if (fakultet != null) {
            this.id = fakultet.getId();
            this.naziv = fakultet.getNaziv();
            this.email = fakultet.getEmail();
            this.opis = fakultet.getOpis();
            this.univerzitet = fakultet.getUniverzitet() != null ? new UniverzitetDTO(fakultet.getUniverzitet()) : null;
            this.dekan = fakultet.getDekan() != null ? new ProfesorDTO(fakultet.getDekan()) : null;
            this.departmani = new ArrayList<>();
            if (fakultet.getDepartmani() != null) {
                fakultet.getDepartmani().forEach(dep -> this.departmani.add(new DepartmanDTO(dep)));
            }
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
    
    public static FakultetDTO fromEntity(Fakultet fakultet) {
        if (fakultet == null) return null;

        UniverzitetDTO univerzitetDTO = fakultet.getUniverzitet() != null
            ? new UniverzitetDTO(fakultet.getUniverzitet())
            : null;

        ProfesorDTO dekanDTO = fakultet.getDekan() != null
            ? new ProfesorDTO(fakultet.getDekan())
            : null;

        ArrayList<DepartmanDTO> departmaniDTO = new ArrayList<>();
        if (fakultet.getDepartmani() != null) {
            fakultet.getDepartmani().forEach(dep -> departmaniDTO.add(new DepartmanDTO(dep)));
        }

        return new FakultetDTO(
            fakultet.getId(),
            fakultet.getNaziv(),
            fakultet.getEmail(),
            fakultet.getOpis(),
            univerzitetDTO,
            dekanDTO,
            departmaniDTO
        );
    }

    public Fakultet toEntity() {
        Fakultet fakultet = new Fakultet();
        fakultet.setId(this.id);
        fakultet.setNaziv(this.naziv);
        fakultet.setEmail(this.email);
        fakultet.setOpis(this.opis);

        if (this.univerzitet != null) {
            fakultet.setUniverzitet(this.univerzitet.toEntity());
        }

        if (this.dekan != null) {
            fakultet.setDekan(this.dekan.toEntity());
        }

        if (this.departmani != null) {
            fakultet.setDepartmani(
                this.departmani.stream()
                    .map(DepartmanDTO::toEntity)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return fakultet;
    }

}