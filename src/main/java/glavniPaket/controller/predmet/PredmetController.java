package glavniPaket.controller.predmet;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.predmet.PredmetDTO;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.service.fakultet.FakultetService;
import glavniPaket.service.predmet.PredmetService;


@RestController
@RequestMapping("/api/predmeti")
public class PredmetController {

    private final PredmetService predmetService;

    @Autowired
    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<PredmetDTO>> getAllPredmeti() {
        return ResponseEntity.ok(predmetService.findAll());
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(predmetService.findById(id));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<PredmetDTO> create(@RequestBody PredmetDTO dto) {
        return ResponseEntity.ok(predmetService.save(dto));
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<PredmetDTO> update(@PathVariable Long id, @RequestBody PredmetDTO dto) {
        return ResponseEntity.ok(predmetService.update(id, dto));
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        predmetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === GET BY NAZIV ===
    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<PredmetDTO> getByNaziv(@PathVariable String naziv) {
        return predmetService.findByNaziv(naziv)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === SEARCH BY PREFIX ===
    @GetMapping("/search/prefix/{prefix}")
    public ResponseEntity<List<PredmetDTO>> searchByPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(predmetService.findByNazivStartingWith(prefix));
    }

    // === SEARCH BY KEYWORD ===
    @GetMapping("/search")
    public ResponseEntity<List<PredmetDTO>> searchByKeyword(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(predmetService.pretraziPoNazivu(keyword));
    }

    // === GET BY ECTS ===
    @GetMapping("/ects/{ects}")
    public ResponseEntity<PredmetDTO> getByEcts(@PathVariable int ects) {
        return predmetService.findByEcts(ects)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}