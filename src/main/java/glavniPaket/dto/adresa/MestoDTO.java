package glavniPaket.dto.adresa;

import glavniPaket.model.adresa.*;

public class MestoDTO {
    private Long id; 
    private String naziv;

    public MestoDTO() {}

    public MestoDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public MestoDTO(Mesto mesto) {
        this.id = mesto.getId();
        this.naziv = mesto.getNaziv();
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
