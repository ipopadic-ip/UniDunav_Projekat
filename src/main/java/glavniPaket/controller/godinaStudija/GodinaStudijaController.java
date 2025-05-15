package glavniPaket.controller.godinaStudija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.service.godinaStudija.GodinaStudijaService;

@RestController
@RequestMapping("/godina-studija")
public class GodinaStudijaController {

    @Autowired
    private GodinaStudijaService godinaStudijaService;

    public GodinaStudijaController(GodinaStudijaService godinaStudijaService) {
        this.godinaStudijaService = godinaStudijaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GodinaStudija> getById(@PathVariable Long id) {
        return godinaStudijaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<GodinaStudija>> getAll() {
        Iterable<GodinaStudija> godineStudija = godinaStudijaService.findAll();
        return ResponseEntity.ok(godineStudija);
    }

    @PostMapping
    public ResponseEntity<GodinaStudija> create(@RequestBody GodinaStudija godinaStudija) {
        GodinaStudija savedGodinaStudija = godinaStudijaService.save(godinaStudija);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGodinaStudija);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GodinaStudija> update(@PathVariable Long id, @RequestBody GodinaStudija godinaStudija) {
        if (godinaStudijaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        godinaStudija.setId(id);
        GodinaStudija updatedGodinaStudija = godinaStudijaService.save(godinaStudija);
        return ResponseEntity.ok(updatedGodinaStudija);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (godinaStudijaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        godinaStudijaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<GodinaStudijaDTO> add(@RequestBody GodinaStudijaDTO godinaStudijaDTO) {
        GodinaStudija novaGodinaStudija = new GodinaStudija(null, godinaStudijaDTO.getGodina(), null, null);
        this.godinaStudijaService.save(novaGodinaStudija);

        return new ResponseEntity<GodinaStudijaDTO>(
            new GodinaStudijaDTO(
                novaGodinaStudija.getId(),
                novaGodinaStudija.getGodina(),
                null,
                null
            ), HttpStatus.CREATED);
    }
}
