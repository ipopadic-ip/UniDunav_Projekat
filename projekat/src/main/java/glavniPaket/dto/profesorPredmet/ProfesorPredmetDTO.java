package glavniPaket.dto.profesorPredmet;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.predmet.PredmetDTO;

public class ProfesorPredmetDTO {

    private Long id;
    private ProfesorDTO profesor;
    private PredmetDTO predmet;
	public ProfesorPredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfesorPredmetDTO(Long id, ProfesorDTO profesor, PredmetDTO predmet) {
		super();
		this.id = id;
		this.profesor = profesor;
		this.predmet = predmet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProfesorDTO getProfesor() {
		return profesor;
	}
	public void setProfesor(ProfesorDTO profesor) {
		this.profesor = profesor;
	}
	public PredmetDTO getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}
	@Override
	public String toString() {
		return "ProfesorPredmetDTO [id=" + id + ", profesor=" + profesor + ", predmet=" + predmet + "]";
	}

    
}
