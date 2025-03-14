package controller.korisnika;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import model.korisnika.*;

import service.korisnika.RegistrovaniKorisnikService;

@RestController
@RequestMapping("/korisnici")
public class RegistrovaniKorisnikController {

    @Autowired
    private RegistrovaniKorisnikService korisnikService;

    public RegistrovaniKorisnikController(RegistrovaniKorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RegistrovaniKorisnik>> getById(@PathVariable Integer id) {
        Optional<RegistrovaniKorisnik> korisnik = korisnikService.findById(id);
        return korisnik != null ? ResponseEntity.ok(korisnik) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<RegistrovaniKorisnik>> getAll() {
        Iterable<RegistrovaniKorisnik> korisnici = korisnikService.findAll();
        return ResponseEntity.ok(korisnici);
    }

    @PostMapping
    public ResponseEntity<RegistrovaniKorisnik> create(@RequestBody RegistrovaniKorisnik korisnik) {
        RegistrovaniKorisnik savedKorisnik = korisnikService.save(korisnik);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKorisnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrovaniKorisnik> update(@PathVariable Integer id, @RequestBody RegistrovaniKorisnik korisnik) {
        if (korisnikService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        korisnik.setId(id);
        RegistrovaniKorisnik updatedKorisnik = korisnikService.save(korisnik);
        return ResponseEntity.ok(updatedKorisnik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (korisnikService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        korisnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
