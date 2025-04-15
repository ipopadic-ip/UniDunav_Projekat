package glavniPaket.controller.predmet;

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

import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.service.fakultet.FakultetService;
import glavniPaket.service.predmet.PredmetService;

@RestController
@RequestMapping("/predmet")
public class PredmetController {
    
    @Autowired
    private PredmetService predmetService;

    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Predmet> getById(@PathVariable Long id) {
        return predmetService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Predmet>> getAll() {
        Iterable<Predmet> predmeti = predmetService.findAll();
        return ResponseEntity.ok(predmeti);
    }

    @PostMapping
    public ResponseEntity<Predmet> create(@RequestBody Predmet predmet) {
        Predmet savedPredmet = predmetService.save(predmet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPredmet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Predmet> update(@PathVariable Long id, @RequestBody Predmet predmet) {
        if (predmetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        predmet.setId(id);
        Predmet updatedPredmet = predmetService.save(predmet);
        return ResponseEntity.ok(updatedPredmet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (predmetService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        predmetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

