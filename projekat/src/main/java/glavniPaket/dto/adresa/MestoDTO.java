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
    public Mesto toEntity() {
        return new Mesto(this.id, this.naziv, null);
    }
}
