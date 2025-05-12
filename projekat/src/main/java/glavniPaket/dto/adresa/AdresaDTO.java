package glavniPaket.dto.adresa;

import glavniPaket.model.adresa.Adresa;

public class AdresaDTO {
    private Long id;
    private String ulica; // Ulica kao String
    private String broj; // Broj sada kao String
    private MestoDTO mesto; // Mesto kao objekat

    public AdresaDTO() {}

    
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUlica() {
		return ulica;
	}



	public void setUlica(String ulica) {
		this.ulica = ulica;
	}



	public String getBroj() {
		return broj;
	}



	public void setBroj(String broj) {
		this.broj = broj;
	}



	public MestoDTO getMesto() {
		return mesto;
	}



	public void setMesto(MestoDTO mesto) {
		this.mesto = mesto;
	}



	public AdresaDTO(Long id, String ulica, String broj, MestoDTO mesto) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.broj = broj;
		this.mesto = mesto;
	}



	public Adresa toEntity() {
        Adresa adresa = new Adresa();
        adresa.setId(this.id);
        adresa.setBroj(this.broj);
        adresa.setUlica(this.ulica);
        
        if (this.mesto != null) {
            adresa.setMesto(this.mesto.toEntity());
        }
        
        return adresa;
    }
    
    // Metoda za konverziju iz Adresa entiteta u AdresaDTO
    public static AdresaDTO fromEntity(Adresa adresa) {
        if (adresa == null) {
            return null;
        }
        return new AdresaDTO(
            adresa.getId(),
            adresa.getBroj(), // Sada je String, pa je kompatibilno
            adresa.getUlica(),
            adresa.getMesto() != null ? new MestoDTO(adresa.getMesto()) : null
        );
    }
}