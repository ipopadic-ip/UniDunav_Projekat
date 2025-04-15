package glavniPaket.dto.adresa;

import glavniPaket.model.adresa.Adresa;

public class AdresaDTO {
    private Integer id;
    private String ulica; // Ulica kao String
    private String broj; // Broj sada kao String
    private MestoDTO mesto; // Mesto kao objekat

    public AdresaDTO() {}

    public AdresaDTO(Integer id, String broj, String ulica, MestoDTO mesto) {
        this.id = id;
        this.broj = broj;
        this.ulica = ulica;
        this.mesto = mesto;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getBroj() { return broj; } // Ispravljeno na String
    public void setBroj(String broj) { this.broj = broj; } // Ispravljeno na String

    public String getUlica() { return ulica; }
    public void setUlica(String ulica) { this.ulica = ulica; }

    public MestoDTO getMesto() { return mesto; }
    public void setMesto(MestoDTO mesto) { this.mesto = mesto; }

    
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