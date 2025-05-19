package glavniPaket.controller.predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.predmet.SilabusDTO;
import glavniPaket.service.predmet.SilabusService;

@RestController
@RequestMapping("/api/silabusi")
public class SilabusController {

    private final SilabusService silabusService;

    @Autowired
    public SilabusController(SilabusService silabusService) {
        this.silabusService = silabusService;
    }

    @GetMapping
    public ResponseEntity<List<SilabusDTO>> getAll() {
        return ResponseEntity.ok(silabusService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SilabusDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(silabusService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SilabusDTO> create(@RequestBody SilabusDTO dto) {
        return ResponseEntity.ok(silabusService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SilabusDTO> update(@PathVariable Long id, @RequestBody SilabusDTO dto) {
        return ResponseEntity.ok(silabusService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        silabusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
