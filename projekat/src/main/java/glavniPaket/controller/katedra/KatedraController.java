package glavniPaket.controller.katedra;

import java.util.Optional;

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
	public ResponseEntity<Optional<Katedra>> getById(@PathVariable Integer id) {
        Optional<Katedra> katedra = katedraService.findById(id);
        return katedra != null ? ResponseEntity.ok(katedra) : ResponseEntity.notFound().build();
    }
	
	@GetMapping
    public ResponseEntity<Iterable<Katedra>> getAll() {
        Iterable<Katedra> katedra = katedraService.findAll();
        return ResponseEntity.ok(katedra);
    }
	
	@PostMapping
    public ResponseEntity<Katedra> create(@RequestBody Katedra katedra) {
		Katedra savedKatedra = katedraService.save(katedra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKatedra);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Katedra> update(@PathVariable Integer id, @RequestBody Katedra katedra) {
        if (katedraService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        katedra.setId(id);
        Katedra updatedKatedra = katedraService.save(katedra);
        return ResponseEntity.ok(updatedKatedra);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (katedraService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        katedraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
