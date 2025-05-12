package glavniPaket.dto.tipStudija;

import java.util.ArrayList;

import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;

public class TipStudijaDTO {
	private Long id;
	private String tip;
	private KatedraDTO katedra;
	private ArrayList<StudijskiProgram> studijskiProgrami = new ArrayList<StudijskiProgram>();
	public TipStudijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipStudijaDTO(Long id, String tip, KatedraDTO katedra, ArrayList<StudijskiProgram> studijskiProgrami) {
		super();
		this.id = id;
		this.tip = tip;
		this.katedra = katedra;
		this.studijskiProgrami = studijskiProgrami;
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
	public KatedraDTO getKatedra() {
		return katedra;
	}
	public void setKatedra(KatedraDTO katedra) {
		this.katedra = katedra;
	}
	public ArrayList<StudijskiProgram> getStudijskiProgrami() {
		return studijskiProgrami;
	}
	public void setStudijskiProgrami(ArrayList<StudijskiProgram> studijskiProgrami) {
		this.studijskiProgrami = studijskiProgrami;
	}
	@Override
	public String toString() {
		return "TipStudijaDTO [id=" + id + ", tip=" + tip + ", katedra=" + katedra + ", studijskiProgrami="
				+ studijskiProgrami + "]";
	}
	
	
}
