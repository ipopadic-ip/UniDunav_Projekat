package glavniPaket.controller.fakultet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.fakultet.FakultetDTO;
import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.service.fakultet.FakultetService;
import glavniPaket.model.univerzitet.Univerzitet;

import java.util.ArrayList;

@RestController
@RequestMapping("/fakultet")
public class FakultetController {

    @Autowired
    private FakultetService fakultetService;

    public FakultetController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fakultet> getById(@PathVariable Long id) {
        return fakultetService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Fakultet>> getAll() {
        Iterable<Fakultet> fakulteti = fakultetService.findAll();
        return ResponseEntity.ok(fakulteti);
    }

    @PostMapping
    public ResponseEntity<Fakultet> create(@RequestBody Fakultet fakultet) {
        Fakultet savedFakultet = fakultetService.save(fakultet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFakultet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fakultet> update(@PathVariable Long id, @RequestBody Fakultet fakultet) {
        if (fakultetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        fakultet.setId(id);
        Fakultet updatedFakultet = fakultetService.save(fakultet);
        return ResponseEntity.ok(updatedFakultet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (fakultetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        fakultetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/dto")
    public ResponseEntity<FakultetDTO> add(@RequestBody FakultetDTO fakultetDTO) {

        Adresa adresaEntity = fakultetDTO.getAdresa() != null ? fakultetDTO.getAdresa() : null; 
        Univerzitet univerzitetEntity = fakultetDTO.getUniverzitet() != null ? fakultetDTO.getUniverzitet() : null;

        Fakultet fakultetEntity = new Fakultet(
            null, 
            fakultetDTO.getNaziv(),
            fakultetDTO.getEmail(),
            adresaEntity,
            univerzitetEntity, 
            new ArrayList<>(), 
            fakultetDTO.getOpis(),
            fakultetDTO.getDekan()
        );

        
        Fakultet savedFakultet = fakultetService.save(fakultetEntity);


        FakultetDTO savedFakultetDTO = FakultetDTO.fromEntity(savedFakultet);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedFakultetDTO);
    }
}
