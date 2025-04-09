package glavniPaket.controller.korisnika;

import glavniPaket.model.korisnika.PravoPristupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import glavniPaket.service.korisnika.*;

import java.util.List;

@RestController
@RequestMapping("/api/prava-pristupa")
public class PravoPristupaController {

    @Autowired
    private PravoPristupaService pravoService;

    @GetMapping
    public ResponseEntity<List<PravoPristupa>> getAll() {
        return ResponseEntity.ok(pravoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PravoPristupa> getOne(@PathVariable Integer id) {
        return pravoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PravoPristupa> create(@RequestBody PravoPristupa pravo) {
        return ResponseEntity.ok(pravoService.save(pravo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pravoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
