package glavniPaket.dto.korisnika;


import glavniPaket.model.korisnika.DodeljenoPravoPristupa;
import jakarta.persistence.*;


public class DodeljenoPravoPristupaDTO {

    private Long id;
    private Long korisnikId;
    private Long pravoPristupaId;

    public DodeljenoPravoPristupaDTO() {}

    public DodeljenoPravoPristupaDTO(Long id, Long korisnikId, Long pravoPristupaId) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.pravoPristupaId = pravoPristupaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getPravoPristupaId() {
        return pravoPristupaId;
    }

    public void setPravoPristupaId(Long pravoPristupaId) {
        this.pravoPristupaId = pravoPristupaId;
    }
}