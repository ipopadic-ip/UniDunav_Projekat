package glavniPaket.controller.univerzitet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.univerzitet.UniverzitetDTO;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.univerzitet.UniverzitetService;

import java.util.ArrayList;

@RestController
@RequestMapping("/univerzitet")
public class UniverzitetController {

    @Autowired
    private UniverzitetService univerzitetService;

    public UniverzitetController(UniverzitetService univerzitetService) {
        this.univerzitetService = univerzitetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> getById(@PathVariable Long id) {
        return univerzitetService.findById(id)
                .map(univerzitet -> ResponseEntity.ok(new UniverzitetDTO(univerzitet)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<UniverzitetDTO>> getAll() {
        Iterable<Univerzitet> univerziteti = univerzitetService.findAll();
        Iterable<UniverzitetDTO> univerzitetDTOs = convertToDTOs(univerziteti);
        return ResponseEntity.ok(univerzitetDTOs);
    }

    @PostMapping
    public ResponseEntity<UniverzitetDTO> create(@RequestBody UniverzitetDTO dto) {
        Univerzitet univerzitet = dto.toEntity(); 
        Univerzitet saved = univerzitetService.save(univerzitet);
        UniverzitetDTO createdDTO = new UniverzitetDTO(saved); 
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> update(@PathVariable Long id, @RequestBody UniverzitetDTO dto) {
        if (univerzitetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Univerzitet univerzitet = dto.toEntity();
        univerzitet.setId(id); 
        Univerzitet updated = univerzitetService.save(univerzitet);
        return ResponseEntity.ok(new UniverzitetDTO(updated)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (univerzitetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        univerzitetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Iterable<UniverzitetDTO> convertToDTOs(Iterable<Univerzitet> univerziteti) {
        ArrayList<UniverzitetDTO> dtos = new ArrayList<>();
        for (Univerzitet univerzitet : univerziteti) {
            dtos.add(new UniverzitetDTO(univerzitet));
        }
        return dtos;
    }
}
