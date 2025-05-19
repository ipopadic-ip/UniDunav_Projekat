package glavniPaket.controller.studijskiProgram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.studijskiProgram.StudijskiProgramDTO;
import glavniPaket.model.studijskiProgram.StudijskiProgram;
import glavniPaket.service.studijskiProgram.StudijskiProgramService;

@RestController
@RequestMapping("/api/studijski-programi")
public class StudijskiProgramController {

    private final StudijskiProgramService studijskiProgramService;

    @Autowired
    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }

    @GetMapping
    public ResponseEntity<List<StudijskiProgramDTO>> getAll() {
        return ResponseEntity.ok(studijskiProgramService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studijskiProgramService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudijskiProgramDTO> create(@RequestBody StudijskiProgramDTO dto) {
        return new ResponseEntity<>(studijskiProgramService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable Long id, @RequestBody StudijskiProgramDTO dto) {
        return ResponseEntity.ok(studijskiProgramService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studijskiProgramService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<StudijskiProgramDTO> getByNaziv(@PathVariable String naziv) {
        return ResponseEntity.ok(studijskiProgramService.findByNaziv(naziv));
    }

    @GetMapping("/pretraga/pocetak/{prefix}")
    public ResponseEntity<List<StudijskiProgramDTO>> searchByPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(studijskiProgramService.findByNazivStartingWith(prefix));
    }

    @GetMapping("/pretraga/{keyword}")
    public ResponseEntity<List<StudijskiProgramDTO>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(studijskiProgramService.pretraziPoNazivu(keyword));
    }
}