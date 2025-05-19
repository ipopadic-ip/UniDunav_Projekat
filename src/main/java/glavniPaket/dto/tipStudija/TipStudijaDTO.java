package glavniPaket.dto.tipStudija;

import java.util.ArrayList;
import java.util.List;

import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.model.tipStudija.TipStudija;

public class TipStudijaDTO {

    private Long id;
    private String tip;

    private Long katedraId;
    private List<Long> studijskiProgramiIds;

    public TipStudijaDTO() {}

    public TipStudijaDTO(Long id, String tip, Long katedraId, List<Long> studijskiProgramiIds) {
        this.id = id;
        this.tip = tip;
        this.katedraId = katedraId;
        this.studijskiProgramiIds = studijskiProgramiIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Long getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Long katedraId) {
        this.katedraId = katedraId;
    }

    public List<Long> getStudijskiProgramiIds() {
        return studijskiProgramiIds;
    }

    public void setStudijskiProgramiIds(List<Long> studijskiProgramiIds) {
        this.studijskiProgramiIds = studijskiProgramiIds;
    }
}