package glavniPaket.dto.studijskiProgram;

import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.tipStudija.TipStudija;

public class StudijskiProgramDTO {
	private Long id;
	private String naziv;
	private String opis;
	private GodinaStudija godinaStudija;
	private TipStudija tipStudija;
	private Profesor rukovodilac;
	public StudijskiProgramDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudijskiProgramDTO(Long id, String naziv, String opis, GodinaStudija godinaStudija, TipStudija tipStudija,
			Profesor rukovodilac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.godinaStudija = godinaStudija;
		this.tipStudija = tipStudija;
		this.rukovodilac = rukovodilac;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public TipStudija getTipStudija() {
		return tipStudija;
	}
	public void setTipStudija(TipStudija tipStudija) {
		this.tipStudija = tipStudija;
	}
	public Profesor getRukovodilac() {
		return rukovodilac;
	}
	public void setRukovodilac(Profesor rukovodilac) {
		this.rukovodilac = rukovodilac;
	}
	
	
}
