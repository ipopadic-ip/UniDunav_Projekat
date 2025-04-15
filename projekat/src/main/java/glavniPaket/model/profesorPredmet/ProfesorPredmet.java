package glavniPaket.model.profesorPredmet;

import jakarta.persistence.*;

import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.predmet.Predmet;

@Entity
@Table(name = "profesor_predmet")
public class ProfesorPredmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    public ProfesorPredmet() {}

    public ProfesorPredmet(Profesor profesor, Predmet predmet) {
        this.profesor = profesor;
        this.predmet = predmet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
