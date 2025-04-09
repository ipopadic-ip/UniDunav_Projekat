package glavniPaket.model.profesorPredmet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.predmet.Predmet;
@Entity
public class ProfesorPredmet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private Profesor profesor;
	
	@Column(nullable=false)
	private Predmet predmet;

	public ProfesorPredmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesorPredmet(Integer id, Profesor profesor, Predmet predmet) {
		super();
		this.id = id;
		this.profesor = profesor;
		this.predmet = predmet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
