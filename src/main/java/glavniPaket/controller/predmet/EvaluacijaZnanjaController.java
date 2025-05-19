package glavniPaket.controller.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.predmet.EvaluacijaZnanjaDTO;
import glavniPaket.service.predmet.EvaluacijaZnanjaService;

import java.util.List;

@RestController
@RequestMapping("/api/evaluacije")
public class EvaluacijaZnanjaController {

    private final EvaluacijaZnanjaService evaluacijaService;

    @Autowired
    public EvaluacijaZnanjaController(EvaluacijaZnanjaService evaluacijaService) {
        this.evaluacijaService = evaluacijaService;
    }

    @GetMapping
    public ResponseEntity<List<EvaluacijaZnanjaDTO>> getAll() {
        return ResponseEntity.ok(evaluacijaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacijaZnanjaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacijaService.findById(id));
    }

    @GetMapping("/pohadjanje/{pohadjanjeId}")
    public ResponseEntity<List<EvaluacijaZnanjaDTO>> getByPohadjanje(@PathVariable Long pohadjanjeId) {
        return ResponseEntity.ok(evaluacijaService.findByPohadjanjeId(pohadjanjeId));
    }

    @GetMapping("/tip/{tipId}")
    public ResponseEntity<List<EvaluacijaZnanjaDTO>> getByTipEvaluacije(@PathVariable Long tipId) {
        return ResponseEntity.ok(evaluacijaService.findByTipEvaluacijeId(tipId));
    }

    @PostMapping
    public ResponseEntity<EvaluacijaZnanjaDTO> create(@RequestBody EvaluacijaZnanjaDTO dto) {
        return ResponseEntity.ok(evaluacijaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacijaZnanjaDTO> update(@PathVariable Long id, @RequestBody EvaluacijaZnanjaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(evaluacijaService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        evaluacijaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}