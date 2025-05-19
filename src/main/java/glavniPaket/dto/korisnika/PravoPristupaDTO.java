package glavniPaket.dto.korisnika;

public class PravoPristupaDTO {
    private Long id;
    private String naziv;
    
	public PravoPristupaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PravoPristupaDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
		
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
	
}