package glavniPaket.controller.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.service.adresa.MestoService;

import java.util.List;

@RestController
@RequestMapping("/api/mesta")
public class MestoController {

    private final MestoService mestoService;

    @Autowired
    public MestoController(MestoService mestoService) {
        this.mestoService = mestoService;
    }

    @GetMapping
    public ResponseEntity<List<MestoDTO>> getAll() {
        return ResponseEntity.ok(mestoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MestoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mestoService.findById(id));
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<MestoDTO>> getByNaziv(@RequestParam String naziv) {
        return ResponseEntity.ok(mestoService.findByNaziv(naziv));
    }

    @PostMapping
    public ResponseEntity<MestoDTO> create(@RequestBody MestoDTO dto) {
        return ResponseEntity.ok(mestoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MestoDTO> update(@PathVariable Long id, @RequestBody MestoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(mestoService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}