package glavniPaket.controller.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.predmet.TipEvaluacijeDTO;
import glavniPaket.service.predmet.TipEvaluacijeService;

import java.util.List;

@RestController
@RequestMapping("/api/tipovi-evaluacije")
public class TipEvaluacijeController {

    private final TipEvaluacijeService tipService;

    @Autowired
    public TipEvaluacijeController(TipEvaluacijeService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public ResponseEntity<List<TipEvaluacijeDTO>> getAll() {
        return ResponseEntity.ok(tipService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipEvaluacijeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipService.findById(id));
    }

    @GetMapping("/tip/{tip}")
    public ResponseEntity<TipEvaluacijeDTO> getByTip(@PathVariable String tip) {
        return ResponseEntity.ok(tipService.findByTip(tip));
    }

    @PostMapping
    public ResponseEntity<TipEvaluacijeDTO> create(@RequestBody TipEvaluacijeDTO dto) {
        return ResponseEntity.ok(tipService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipEvaluacijeDTO> update(@PathVariable Long id, @RequestBody TipEvaluacijeDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(tipService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
