package glavniPaket.dto.godinaStudija;

import java.util.ArrayList;

import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.studijskiProgram.StudijskiProgram;

public class GodinaStudijaDTO {
	private Long id;
	private int godina;
	private StudijskiProgram studijskiProgram;
	private ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	public GodinaStudijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GodinaStudijaDTO(Long id, int godina, StudijskiProgram studijskiProgram, ArrayList<Predmet> predmeti) {
		super();
		this.id = id;
		this.godina = godina;
		this.studijskiProgram = studijskiProgram;
		this.predmeti = predmeti;
	}
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
	public StudijskiProgram getStudijskiProgram() {
		return studijskiProgram;
	}
	public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}
	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	@Override
	public String toString() {
		return "GodinaStudijaDTO [id=" + id + ", godina=" + godina + ", studijskiProgram=" + studijskiProgram
				+ ", predmeti=" + predmeti + "]";
	}
	
}
