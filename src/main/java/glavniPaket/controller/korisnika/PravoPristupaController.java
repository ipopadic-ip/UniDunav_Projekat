package glavniPaket.controller.korisnika;

import glavniPaket.dto.korisnika.PravoPristupaDTO;
import glavniPaket.model.korisnika.PravoPristupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import glavniPaket.service.korisnika.*;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prava-pristupa")
@CrossOrigin(origins = "*") // ako koristiš sa frontendom
public class PravoPristupaController {

    private final PravoPristupaService pravoPristupaService;

    @Autowired
    public PravoPristupaController(PravoPristupaService pravoPristupaService) {
        this.pravoPristupaService = pravoPristupaService;
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<PravoPristupaDTO>> getAll() {
        return ResponseEntity.ok(pravoPristupaService.findAll());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<PravoPristupaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pravoPristupaService.findById(id));
    }

    // POST - create
    @PostMapping
    public ResponseEntity<PravoPristupaDTO> create(@RequestBody PravoPristupaDTO dto) {
        return ResponseEntity.ok(pravoPristupaService.save(dto));
    }

    // PUT - update
    @PutMapping("/{id}")
    public ResponseEntity<PravoPristupaDTO> update(@PathVariable Long id, @RequestBody PravoPristupaDTO dto) {
        return ResponseEntity.ok(pravoPristupaService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pravoPristupaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}