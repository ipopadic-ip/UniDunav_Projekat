package glavniPaket.dto.predmet;

public class TipEvaluacijeDTO {
    private Long id;
    private String tip;

    public TipEvaluacijeDTO() {}

    public TipEvaluacijeDTO(Long id, String tip) {
        this.id = id;
        this.tip = tip;
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
}
