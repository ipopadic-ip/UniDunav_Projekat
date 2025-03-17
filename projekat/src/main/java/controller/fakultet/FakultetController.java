package controller.fakultet;

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

import model.fakultet.Fakultet;
import model.univerzitet.Univerzitet;
import service.fakultet.FakultetService;
import service.univerzitet.UniverzitetService;

@RestController
@RequestMapping("/fakultet")
public class FakultetController {
	@Autowired
	private FakultetService fakultetService;
	
	public FakultetController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Fakultet>> getById(@PathVariable Integer id) {
        Optional<Fakultet> fakultet = fakultetService.findById(id);
        return fakultet != null ? ResponseEntity.ok(fakultet) : ResponseEntity.notFound().build();
    }
	
	@GetMapping
    public ResponseEntity<Iterable<Fakultet>> getAll() {
        Iterable<Fakultet> fakultet = fakultetService.findAll();
        return ResponseEntity.ok(fakultet);
    }
	
	@PostMapping
    public ResponseEntity<Fakultet> create(@RequestBody Fakultet fakultet) {
		Fakultet savedFakultet = fakultetService.save(fakultet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFakultet);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Fakultet> update(@PathVariable Integer id, @RequestBody Fakultet fakultet) {
        if (fakultetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        fakultet.setId(id);
        Fakultet updatedFakultet = fakultetService.save(fakultet);
        return ResponseEntity.ok(updatedFakultet);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (fakultetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        fakultetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
