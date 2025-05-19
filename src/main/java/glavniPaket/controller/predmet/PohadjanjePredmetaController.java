package glavniPaket.controller.predmet;

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

import glavniPaket.dto.predmet.PohadjanjePredmetaDTO;
import glavniPaket.service.predmet.PohadjanjePredmetaService;

@RestController
@RequestMapping("/api/pohadjanja")
public class PohadjanjePredmetaController {

    private final PohadjanjePredmetaService pohadjanjeService;

    @Autowired
    public PohadjanjePredmetaController(PohadjanjePredmetaService pohadjanjeService) {
        this.pohadjanjeService = pohadjanjeService;
    }

    @GetMapping
    public ResponseEntity<List<PohadjanjePredmetaDTO>> getAll() {
        return ResponseEntity.ok(pohadjanjeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PohadjanjePredmetaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pohadjanjeService.findById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PohadjanjePredmetaDTO>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(pohadjanjeService.findByStudentId(studentId));
    }

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<PohadjanjePredmetaDTO>> getByPredmetId(@PathVariable Long predmetId) {
        return ResponseEntity.ok(pohadjanjeService.findByPredmetId(predmetId));
    }

    @PostMapping
    public ResponseEntity<PohadjanjePredmetaDTO> create(@RequestBody PohadjanjePredmetaDTO dto) {
        return ResponseEntity.ok(pohadjanjeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PohadjanjePredmetaDTO> update(@PathVariable Long id,
                                                        @RequestBody PohadjanjePredmetaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(pohadjanjeService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pohadjanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}