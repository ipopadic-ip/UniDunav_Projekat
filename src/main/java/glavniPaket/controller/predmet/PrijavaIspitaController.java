package glavniPaket.controller.predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.predmet.PrijavaIspitaDTO;
import glavniPaket.service.predmet.PrijavaIspitaService;

import java.util.List;

@RestController
@RequestMapping("/api/prijave-ispita")
public class PrijavaIspitaController {

    private final PrijavaIspitaService prijavaService;

    @Autowired
    public PrijavaIspitaController(PrijavaIspitaService prijavaService) {
        this.prijavaService = prijavaService;
    }

    @GetMapping
    public ResponseEntity<List<PrijavaIspitaDTO>> getAll() {
        return ResponseEntity.ok(prijavaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrijavaIspitaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(prijavaService.findById(id));
    }

    @GetMapping("/pohadjanje/{pohadjanjeId}")
    public ResponseEntity<List<PrijavaIspitaDTO>> getByPohadjanjeId(@PathVariable Long pohadjanjeId) {
        return ResponseEntity.ok(prijavaService.findByPohadjanjeId(pohadjanjeId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PrijavaIspitaDTO>> getByStatus(@PathVariable boolean status) {
        return ResponseEntity.ok(prijavaService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<PrijavaIspitaDTO> create(@RequestBody PrijavaIspitaDTO dto) {
        return ResponseEntity.ok(prijavaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrijavaIspitaDTO> update(@PathVariable Long id, @RequestBody PrijavaIspitaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(prijavaService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prijavaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
