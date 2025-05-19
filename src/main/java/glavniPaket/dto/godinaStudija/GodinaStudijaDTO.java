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

    private Long studijskiProgramId;
    private List<Long> predmetIds;

    public GodinaStudijaDTO() {}

    public GodinaStudijaDTO(Long id, int godina, Long studijskiProgramId, List<Long> predmetIds) {
        this.id = id;
        this.godina = godina;
        this.studijskiProgramId = studijskiProgramId;
        this.predmetIds = predmetIds;
    }

    // === GETTERI I SETTERI ===

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

    public Long getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Long studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public List<Long> getPredmetIds() {
        return predmetIds;
    }

    public void setPredmetIds(List<Long> predmetIds) {
        this.predmetIds = predmetIds;
    }
}