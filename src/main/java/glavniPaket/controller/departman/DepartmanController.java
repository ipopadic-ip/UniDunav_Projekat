package glavniPaket.controller.departman;

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
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.departman.DepartmanDTO;
import glavniPaket.service.departman.DepartmanService;

@RestController
@RequestMapping("/api/departmani")
public class DepartmanController {

    private final DepartmanService departmanService;

    @Autowired
    public DepartmanController(DepartmanService departmanService) {
        this.departmanService = departmanService;
    }

    // === GET ALL ===
    @GetMapping
    public ResponseEntity<List<DepartmanDTO>> getAll() {
        return ResponseEntity.ok(departmanService.findAll());
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<DepartmanDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmanService.findById(id));
    }

    // === CREATE ===
    @PostMapping
    public ResponseEntity<DepartmanDTO> create(@RequestBody DepartmanDTO dto) {
        DepartmanDTO saved = departmanService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<DepartmanDTO> update(@PathVariable Long id, @RequestBody DepartmanDTO dto) {
        DepartmanDTO updated = departmanService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === GET BY NAZIV ===
    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<DepartmanDTO> getByNaziv(@PathVariable String naziv) {
        return ResponseEntity.ok(departmanService.findByNaziv(naziv));
    }

    // === SEARCH BY PREFIX ===
    @GetMapping("/pretraga/pocetak/{prefix}")
    public ResponseEntity<List<DepartmanDTO>> getByPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(departmanService.findByNazivStartingWith(prefix));
    }

    // === SEARCH BY KEYWORD (LIKE) ===
    @GetMapping("/pretraga/{keyword}")
    public ResponseEntity<List<DepartmanDTO>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(departmanService.pretraziPoNazivu(keyword));
    }

    // === EXISTS BY NAZIV ===
    @GetMapping("/postoji/naziv/{naziv}")
    public ResponseEntity<Boolean> existsByNaziv(@PathVariable String naziv) {
        return ResponseEntity.ok(departmanService.existsByNaziv(naziv));
    }
}

