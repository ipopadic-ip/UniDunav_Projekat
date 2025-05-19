package glavniPaket.controller.katedra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.service.katedra.KatedraService;

@RestController
@RequestMapping("/api/katedre")
public class KatedraController {

    private final KatedraService katedraService;

    @Autowired
    public KatedraController(KatedraService katedraService) {
        this.katedraService = katedraService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<KatedraDTO>> getAll() {
        return ResponseEntity.ok(katedraService.findAll());
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<KatedraDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(katedraService.findById(id));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<KatedraDTO> create(@RequestBody KatedraDTO dto) {
        KatedraDTO saved = katedraService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<KatedraDTO> update(@PathVariable Long id, @RequestBody KatedraDTO dto) {
        KatedraDTO updated = katedraService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        katedraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === GET BY NAZIV ===
    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<KatedraDTO> getByNaziv(@PathVariable String naziv) {
        return ResponseEntity.ok(katedraService.findByNaziv(naziv));
    }

    // === SEARCH BY PREFIX ===
    @GetMapping("/pretraga/pocetak/{prefix}")
    public ResponseEntity<List<KatedraDTO>> searchByPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(katedraService.findByNazivStartingWith(prefix));
    }

    // === SEARCH BY KEYWORD ===
    @GetMapping("/pretraga/{keyword}")
    public ResponseEntity<List<KatedraDTO>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(katedraService.pretraziPoNazivu(keyword));
    }
}