package glavniPaket.controller.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.predmet.PrijavaPrestupaDTO;
import glavniPaket.service.predmet.PrijavaPrestupaService;

import java.util.List;

@RestController
@RequestMapping("/api/prijave-prestupa")
public class PrijavaPrestupaController {

    private final PrijavaPrestupaService prijavaService;

    @Autowired
    public PrijavaPrestupaController(PrijavaPrestupaService prijavaService) {
        this.prijavaService = prijavaService;
    }

    @GetMapping
    public ResponseEntity<List<PrijavaPrestupaDTO>> getAll() {
        return ResponseEntity.ok(prijavaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrijavaPrestupaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(prijavaService.findById(id));
    }

    @GetMapping("/pohadjanje/{pohadjanjeId}")
    public ResponseEntity<List<PrijavaPrestupaDTO>> getByPohadjanjeId(@PathVariable Long pohadjanjeId) {
        return ResponseEntity.ok(prijavaService.findByPohadjanjeId(pohadjanjeId));
    }

    @PostMapping
    public ResponseEntity<PrijavaPrestupaDTO> create(@RequestBody PrijavaPrestupaDTO dto) {
        return ResponseEntity.ok(prijavaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrijavaPrestupaDTO> update(@PathVariable Long id, @RequestBody PrijavaPrestupaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(prijavaService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prijavaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
