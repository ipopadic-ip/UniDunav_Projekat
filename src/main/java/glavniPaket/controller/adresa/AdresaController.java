package glavniPaket.controller.adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.adresa.AdresaDTO;
import glavniPaket.service.adresa.AdresaService;

import java.util.List;

@RestController
@RequestMapping("/api/adrese")
public class AdresaController {

    private final AdresaService adresaService;

    @Autowired
    public AdresaController(AdresaService adresaService) {
        this.adresaService = adresaService;
    }

    @GetMapping
    public ResponseEntity<List<AdresaDTO>> getAll() {
        return ResponseEntity.ok(adresaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(adresaService.findById(id));
    }

    @GetMapping("/mesto/{mestoId}")
    public ResponseEntity<List<AdresaDTO>> getByMestoId(@PathVariable Long mestoId) {
        return ResponseEntity.ok(adresaService.findByMestoId(mestoId));
    }

    @GetMapping("/ulica")
    public ResponseEntity<List<AdresaDTO>> getByUlica(@RequestParam String naziv) {
        return ResponseEntity.ok(adresaService.findByUlica(naziv));
    }

    @PostMapping
    public ResponseEntity<AdresaDTO> create(@RequestBody AdresaDTO dto) {
        return ResponseEntity.ok(adresaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresaDTO> update(@PathVariable Long id, @RequestBody AdresaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(adresaService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
