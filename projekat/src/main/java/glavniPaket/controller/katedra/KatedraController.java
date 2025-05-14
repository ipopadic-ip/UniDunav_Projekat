package glavniPaket.controller.katedra;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.katedra.KatedraDTO;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.service.katedra.KatedraService;

@RestController
@RequestMapping("/katedra")
public class KatedraController {

    @Autowired
    private KatedraService katedraService;

    public KatedraController(KatedraService katedraService) {
        this.katedraService = katedraService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Katedra> getById(@PathVariable Long id) {
        return katedraService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Katedra>> getAll() {
        Iterable<Katedra> katedre = katedraService.findAll();
        return ResponseEntity.ok(katedre);
    }

    @PostMapping
    public ResponseEntity<Katedra> create(@RequestBody Katedra katedra) {
        Katedra savedKatedra = katedraService.save(katedra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKatedra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Katedra> update(@PathVariable Long id, @RequestBody Katedra katedra) {
        if (katedraService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        katedra.setId(id);
        Katedra updatedKatedra = katedraService.save(katedra);
        return ResponseEntity.ok(updatedKatedra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (katedraService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        katedraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/dto")
    public ResponseEntity<KatedraDTO> add(@RequestBody KatedraDTO katedraDTO) {

        Katedra katedra = new Katedra();
        katedra.setNaziv(katedraDTO.getNaziv());
        katedra.setOpis(katedraDTO.getOpis());

        Katedra saved = katedraService.save(katedra);

        KatedraDTO responseDTO = new KatedraDTO(saved);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
