package glavniPaket.dto.adresa;

import glavniPaket.model.adresa.*;

public class MestoDTO {
    private Integer id; // Promenjeno na Integer ako je tako u tvojoj bazi
    private String naziv;

    public MestoDTO() {}

    public MestoDTO(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public MestoDTO(Mesto mesto) {
        this.id = mesto.getId();
        this.naziv = mesto.getNaziv();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
//    public Mesto toEntity() {
//        return new Mesto(this.id, this.naziv, null);
//    }
//    public Mesto toEntity() {
//        return this.id != null ? new Mesto(this.id) : null;
//    }

    public Mesto toEntity() {
//        Mesto mesto = new Mesto();
//        mesto.setId(this.id);
//        mesto.setNaziv(this.naziv);
//        // NE postavljaj drzava na null
//        return mesto;
    	
//    	 if (this.id == null) return null;

	    // samo postavi ID, Hibernate će to povezati
//	    return new Mesto(this.id);
    	
    	 if (this.id == null) return null;

	    Mesto mesto = new Mesto();
	    mesto.setId(this.id);
	    // OVO JE BITNO: da ne ostane `drzava == null` ako je to zabranjeno u entitetu
	    mesto.setDrzava(new Drzava()); // Dummy objekat, samo da izbegne null
	    return mesto;
    }

}
