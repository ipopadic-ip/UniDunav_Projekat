package glavniPaket.dto.univerzitet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.dto.fakultet.FakultetDTO;

public class UniverzitetDTO {
    private Long id;
    private String naziv;
    private String email;
    private String brojTelefona;
    private String opis;
    private AdresaDTO adresa;
    private List<FakultetDTO> fakulteti = new ArrayList<>();
    private ProfesorDTO rektor;
    

    public UniverzitetDTO() {}
    
    public UniverzitetDTO(Univerzitet univerzitet) {
        if (univerzitet != null) {
            this.id = univerzitet.getId();
            this.naziv = univerzitet.getNaziv();
            this.email = univerzitet.getEmail();
            this.brojTelefona = univerzitet.getBrojTelefona();
            this.opis = univerzitet.getOpis();
            this.adresa = univerzitet.getAdresa() != null ? new AdresaDTO(univerzitet.getAdresa()) : null;

            if (univerzitet.getFakulteti() != null) {
                for (var f : univerzitet.getFakulteti()) {
                    this.fakulteti.add(new FakultetDTO(f));
                }
            }

            this.rektor = univerzitet.getRektor() != null ? new ProfesorDTO(univerzitet.getRektor()) : null;
        }
    }


    public UniverzitetDTO(Long id, String naziv, String email, String brojTelefona, String opis,AdresaDTO adresa,
                          ArrayList<FakultetDTO> fakulteti, ProfesorDTO rektor) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.opis = opis;
        this.adresa = adresa;
        this.fakulteti = fakulteti;
        this.rektor = rektor;
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(List<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public ProfesorDTO getRektor() {
        return rektor;
    }

    public void setRektor(ProfesorDTO rektor) {
        this.rektor = rektor;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }
    
    public Univerzitet toEntity() {
        Univerzitet univerzitet = new Univerzitet();
        univerzitet.setId(this.id);
        univerzitet.setNaziv(this.naziv);
        univerzitet.setEmail(this.email);
        univerzitet.setBrojTelefona(this.brojTelefona);
        univerzitet.setOpis(this.opis);

        if (this.adresa != null) {
            univerzitet.setAdresa(this.adresa.toEntity());
        }

        if (this.rektor != null) {
            univerzitet.setRektor(this.rektor.toEntity());
        }

        if (this.fakulteti != null) {
            univerzitet.setFakulteti(
                this.fakulteti.stream()
                    .map(FakultetDTO::toEntity)
                    .collect(Collectors.toList())
            );
        }

        return univerzitet;
    }


}