package glavniPaket.controller.univerzitet;

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

import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.univerzitet.UniverzitetService;

@RestController
@RequestMapping("/univerzitet")
public class UniverzitetController {
	
	@Autowired
	private UniverzitetService univerzitetService;
	
	public UniverzitetController(UniverzitetService univerzitetService) {
        this.univerzitetService = univerzitetService;
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional<Univerzitet>> getById(@PathVariable Integer id) {
        Optional<Univerzitet> univerzitet = univerzitetService.findById(id);
        return univerzitet != null ? ResponseEntity.ok(univerzitet) : ResponseEntity.notFound().build();
    }
	
	@GetMapping
    public ResponseEntity<Iterable<Univerzitet>> getAll() {
        Iterable<Univerzitet> univerzitet = univerzitetService.findAll();
        return ResponseEntity.ok(univerzitet);
    }
	
	@PostMapping
    public ResponseEntity<Univerzitet> create(@RequestBody Univerzitet univerzitet) {
		Univerzitet savedUniverzitet = univerzitetService.save(univerzitet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUniverzitet);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Univerzitet> update(@PathVariable Integer id, @RequestBody Univerzitet univerzitet) {
        if (univerzitetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        univerzitet.setId(id);
        Univerzitet updatedUniverzitet = univerzitetService.save(univerzitet);
        return ResponseEntity.ok(updatedUniverzitet);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (univerzitetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        univerzitetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
