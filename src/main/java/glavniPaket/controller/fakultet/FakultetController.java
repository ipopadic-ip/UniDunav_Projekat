package glavniPaket.controller.fakultet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.departman.Departman;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.fakultet.FakultetService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fakulteti")
public class FakultetController {

    private final FakultetService fakultetService;

    @Autowired
    public FakultetController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<FakultetDTO>> getAll() {
        return ResponseEntity.ok(fakultetService.findAll());
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<FakultetDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fakultetService.findById(id));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<FakultetDTO> create(@RequestBody FakultetDTO dto) {
        FakultetDTO saved = fakultetService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<FakultetDTO> update(@PathVariable Long id, @RequestBody FakultetDTO dto) {
        FakultetDTO updated = fakultetService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fakultetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === GET BY NAZIV ===
    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<FakultetDTO> getByNaziv(@PathVariable String naziv) {
        return ResponseEntity.ok(fakultetService.findByNaziv(naziv));
    }

    // === SEARCH BY PREFIX ===
    @GetMapping("/pretraga/pocetak/{prefix}")
    public ResponseEntity<List<FakultetDTO>> getByNazivPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(fakultetService.findByNazivStartingWith(prefix));
    }

    // === SEARCH BY KEYWORD (LIKE %keyword%) ===
    @GetMapping("/pretraga/{keyword}")
    public ResponseEntity<List<FakultetDTO>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(fakultetService.pretraziPoNazivu(keyword));
    }

    // === EXISTS BY EMAIL ===
    @GetMapping("/postoji/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(fakultetService.existsByEmail(email));
    }
}