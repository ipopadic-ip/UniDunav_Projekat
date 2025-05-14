package glavniPaket.dto.godinaStudija;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.studijskiProgram.StudijskiProgram;

public class GodinaStudijaDTO {
    private Long id;
    private int godina;
    private StudijskiProgramDTO studijskiProgram;
    private List<PredmetDTO> predmeti = new ArrayList<>();

    // Default constructor
    public GodinaStudijaDTO() {
        super();
    }

    // Constructor with parameters
    public GodinaStudijaDTO(Long id, int godina, StudijskiProgramDTO studijskiProgram, List<PredmetDTO> predmeti) {
        this.id = id;
        this.godina = godina;
        this.studijskiProgram = studijskiProgram;
        this.predmeti = predmeti;
    }

    // Constructor that takes an entity and converts it to DTO
    public GodinaStudijaDTO(GodinaStudija godinaStudija) {
        this.id = godinaStudija.getId();
        this.godina = godinaStudija.getGodina();
        this.studijskiProgram = godinaStudija.getStudijskiProgram() != null ? new StudijskiProgramDTO(godinaStudija.getStudijskiProgram()) : null;
        this.predmeti = godinaStudija.getPredmeti() != null
                ? godinaStudija.getPredmeti().stream().map(PredmetDTO::new).collect(Collectors.toList())
                : new ArrayList<>();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public StudijskiProgramDTO getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public GodinaStudija toEntity() {
        List<Predmet> predmetiEntities = this.predmeti.stream()
                .map(PredmetDTO::toEntity)
                .collect(Collectors.toList());

        StudijskiProgram studijskiProgramEntity = this.studijskiProgram != null
                ? this.studijskiProgram.toEntity()
                : null;

        return new GodinaStudija(this.id, this.godina, studijskiProgramEntity, (ArrayList<Predmet>) predmetiEntities);
    }

    // Override toString for better representation
    @Override
    public String toString() {
        return "GodinaStudijaDTO [id=" + id + ", godina=" + godina + ", studijskiProgram=" + studijskiProgram
                + ", predmeti=" + predmeti + "]";
    }
}
