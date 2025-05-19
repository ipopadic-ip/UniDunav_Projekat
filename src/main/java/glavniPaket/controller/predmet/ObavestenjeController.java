package glavniPaket.controller.predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.predmet.ObavestenjeDTO;
import glavniPaket.service.predmet.ObavestenjeService;

@RestController
@RequestMapping("/api/obavestenja")
public class ObavestenjeController {

    private final ObavestenjeService obavestenjeService;

    @Autowired
    public ObavestenjeController(ObavestenjeService obavestenjeService) {
        this.obavestenjeService = obavestenjeService;
    }

    @GetMapping
    public List<ObavestenjeDTO> getAll() {
        return obavestenjeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(obavestenjeService.findById(id));
    }

    @GetMapping("/predmet/{predmetId}")
    public List<ObavestenjeDTO> getByPredmetId(@PathVariable Long predmetId) {
        return obavestenjeService.findByPredmetId(predmetId);
    }

    @PostMapping
    public ResponseEntity<ObavestenjeDTO> create(@RequestBody ObavestenjeDTO dto) {
        return ResponseEntity.ok(obavestenjeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> update(@PathVariable Long id, @RequestBody ObavestenjeDTO dto) {
        return ResponseEntity.ok(obavestenjeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        obavestenjeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
