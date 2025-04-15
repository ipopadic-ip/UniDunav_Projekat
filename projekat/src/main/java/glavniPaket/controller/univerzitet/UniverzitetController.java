package glavniPaket.controller.univerzitet;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.univerzitet.UniverzitetDTO;
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
    public ResponseEntity<Univerzitet> getById(@PathVariable Long id) {
        return univerzitetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Univerzitet>> getAll() {
        return ResponseEntity.ok(univerzitetService.findAll());
    }

    @PostMapping
    public ResponseEntity<Univerzitet> create(@RequestBody Univerzitet univerzitet) {
        Univerzitet saved = univerzitetService.save(univerzitet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Univerzitet> update(@PathVariable Long id, @RequestBody Univerzitet univerzitet) {
        if (univerzitetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        univerzitet.setId(id);
        Univerzitet updated = univerzitetService.save(univerzitet);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (univerzitetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        univerzitetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/dto")
    public ResponseEntity<UniverzitetDTO> add(@RequestBody UniverzitetDTO dto) {
        Univerzitet univerzitet = new Univerzitet(
            null,
            dto.getNaziv(),
            dto.getEmail(),
            dto.getBrojTelefona(),
            dto.getOpis(),
            new ArrayList<>() 
        );

        univerzitetService.save(univerzitet);

        UniverzitetDTO createdDTO = new UniverzitetDTO(
            univerzitet.getId(),
            univerzitet.getNaziv(),
            univerzitet.getEmail(),
            univerzitet.getBrojTelefona(),
            univerzitet.getOpis(),
            new ArrayList<>(), 
            null 
        );

        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }
}
