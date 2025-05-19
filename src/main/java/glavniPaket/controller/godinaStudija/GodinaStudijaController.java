package glavniPaket.controller.godinaStudija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import glavniPaket.dto.godinaStudija.GodinaStudijaDTO;
import glavniPaket.model.godinaStudija.GodinaStudija;
import glavniPaket.service.godinaStudija.GodinaStudijaService;

@RestController
@RequestMapping("/api/godine-studija")
public class GodinaStudijaController {

    private final GodinaStudijaService godinaStudijaService;

    @Autowired
    public GodinaStudijaController(GodinaStudijaService godinaStudijaService) {
        this.godinaStudijaService = godinaStudijaService;
    }

    @GetMapping
    public ResponseEntity<List<GodinaStudijaDTO>> getAll() {
        return ResponseEntity.ok(godinaStudijaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(godinaStudijaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GodinaStudijaDTO> create(@RequestBody GodinaStudijaDTO dto) {
        GodinaStudijaDTO saved = godinaStudijaService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> update(@PathVariable Long id, @RequestBody GodinaStudijaDTO dto) {
        return ResponseEntity.ok(godinaStudijaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        godinaStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }

   
}