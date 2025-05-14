package glavniPaket.dto.korisnika;


import glavniPaket.model.korisnika.DodeljenoPravoPristupa;
import jakarta.persistence.*;


public class DodeljenoPravoPristupaDTO {
    private Long id;
    private Integer korisnikId;
    private Integer pravoPristupaId;
	public DodeljenoPravoPristupaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DodeljenoPravoPristupaDTO(Long id, Integer korisnikId, Integer pravoPristupaId) {
		super();
		this.id = id;
		this.korisnikId = korisnikId;
		this.pravoPristupaId = pravoPristupaId;
	}
	
	public DodeljenoPravoPristupaDTO(DodeljenoPravoPristupa dodeljenoPravoPristupa) {
        this.id = dodeljenoPravoPristupa.getId();
//        this.korisnikId = dodeljenoPravoPristupa.getKorisnik();
//        this.pravoPristupaId = dodeljenoPravoPristupa.getPravoPristupa();
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}
	public Integer getPravoPristupaId() {
		return pravoPristupaId;
	}
	public void setPravoPristupaId(Integer pravoPristupaId) {
		this.pravoPristupaId = pravoPristupaId;
	}

}
