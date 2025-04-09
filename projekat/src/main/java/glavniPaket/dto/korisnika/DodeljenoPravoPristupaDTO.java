package glavniPaket.dto.korisnika;


import jakarta.persistence.*;


public class DodeljenoPravoPristupaDTO {
    private Integer id;
    private Integer korisnikId;
    private Integer pravoPristupaId;

    public DodeljenoPravoPristupaDTO() {}

    public DodeljenoPravoPristupaDTO(Integer id, Integer korisnikId, Integer pravoPristupaId) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.pravoPristupaId = pravoPristupaId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
