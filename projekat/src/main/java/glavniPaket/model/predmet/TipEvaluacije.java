package glavniPaket.model.predmet;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipovi_evaluacije")
public class TipEvaluacije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tip;

    @OneToMany(mappedBy = "tipEvaluacije", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacijaZnanja> evaluacije;

    public TipEvaluacije() {}

    public TipEvaluacije(String tip) {
        this.tip = tip;
    }

    // Getteri i setteri

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

    public List<EvaluacijaZnanja> getEvaluacije() {
        return evaluacije;
    }

    public void setEvaluacije(List<EvaluacijaZnanja> evaluacije) {
        this.evaluacije = evaluacije;
    }
}

