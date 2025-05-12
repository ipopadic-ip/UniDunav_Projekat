package glavniPaket.dto.profesorPredmet;

public class IshodDTO {
    private Long id;
    private String tema;

    public IshodDTO() {}

    public IshodDTO(Long id, String tema) {
        this.id = id;
        this.tema = tema;
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
}