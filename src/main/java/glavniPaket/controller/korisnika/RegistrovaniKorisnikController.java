package glavniPaket.controller.korisnika;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.korisnika.RegistrovaniKorisnikDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.service.korisnika.RegistrovaniKorisnikService;

@RestController
@RequestMapping("/api/korisnici")
public class RegistrovaniKorisnikController {

    private final RegistrovaniKorisnikService korisnikService;

    @Autowired
    public RegistrovaniKorisnikController(RegistrovaniKorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> getAll() {
        return ResponseEntity.ok(korisnikService.findAll());
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(korisnikService.findById(id));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<RegistrovaniKorisnikDTO> create(@RequestBody RegistrovaniKorisnikDTO dto) {
        RegistrovaniKorisnikDTO created = korisnikService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> update(@PathVariable Long id, @RequestBody RegistrovaniKorisnikDTO dto) {
        return ResponseEntity.ok(korisnikService.update(id, dto));
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        korisnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === GET BY KORISNICKO IME ===
    @GetMapping("/korisnicko-ime/{korisnickoIme}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getByKorisnickoIme(@PathVariable String korisnickoIme) {
        return ResponseEntity.ok(korisnikService.findByKorisnickoIme(korisnickoIme));
    }

    // === GET BY EMAIL ===
    @GetMapping("/email/{email}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(korisnikService.findByEmail(email));
    }

    // === SEARCH: ime + prezime ===
    @GetMapping("/pretraga")
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> searchByImeIPrezime(
            @RequestParam String ime,
            @RequestParam String prezime) {
        return ResponseEntity.ok(korisnikService.findByImeAndPrezime(ime, prezime));
    }

    // === SEARCH: ime počinje sa... ===
    @GetMapping("/ime-pocetak/{prefix}")
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> searchByImePrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(korisnikService.findByImeStartingWith(prefix));
    }

    // === SEARCH: delimično ime ili prezime ===
    @GetMapping("/pretraga-kljucna-rec")
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(korisnikService.pretraziPoImenuIliPrezimenu(keyword));
    }

    // === PROVERA EMAIL ===
    @GetMapping("/postoji-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(korisnikService.existsByEmail(email));
    }

    // === PROVERA JMBG ===
    @GetMapping("/postoji-jmbg")
    public ResponseEntity<Boolean> checkJmbgExists(@RequestParam String jmbg) {
        return ResponseEntity.ok(korisnikService.existsByJmbg(jmbg));
    }
}