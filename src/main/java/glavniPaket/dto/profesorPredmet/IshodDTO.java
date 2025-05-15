package glavniPaket.dto.profesorPredmet;

import glavniPaket.model.profesorPredmet.Ishod;

public class IshodDTO {
    private Long id;
    private String tema;

    public IshodDTO() {}

    public IshodDTO(Long id, String tema) {
        this.id = id;
        this.tema = tema;
    }
    
    public IshodDTO(Ishod ishod) {
        this.id = ishod.getId();
        this.tema = ishod.getTema();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public Ishod toEntity() {
        Ishod ishod = new Ishod();
        ishod.setId(this.id);
        ishod.setTema(this.tema);
        return ishod;
    }
}