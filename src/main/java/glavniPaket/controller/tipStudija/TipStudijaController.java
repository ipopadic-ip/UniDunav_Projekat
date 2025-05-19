package glavniPaket.controller.tipStudija;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.tipStudija.TipStudijaDTO;
import glavniPaket.model.tipStudija.TipStudija;
import glavniPaket.service.tipStudija.TipStudijaService;


@RestController
@RequestMapping("/api/tipovi-studija")
public class TipStudijaController {

    private final TipStudijaService tipStudijaService;

    @Autowired
    public TipStudijaController(TipStudijaService tipStudijaService) {
        this.tipStudijaService = tipStudijaService;
    }

    @GetMapping
    public ResponseEntity<List<TipStudijaDTO>> getAll() {
        return ResponseEntity.ok(tipStudijaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipStudijaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipStudijaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipStudijaDTO> create(@RequestBody TipStudijaDTO dto) {
        return new ResponseEntity<>(tipStudijaService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipStudijaDTO> update(@PathVariable Long id, @RequestBody TipStudijaDTO dto) {
        return ResponseEntity.ok(tipStudijaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tip/{tip}")
    public ResponseEntity<TipStudijaDTO> getByTip(@PathVariable String tip) {
        return ResponseEntity.ok(tipStudijaService.findByTip(tip));
    }

    @GetMapping("/pretraga/pocetak/{prefix}")
    public ResponseEntity<List<TipStudijaDTO>> searchByPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(tipStudijaService.findByTipStartingWith(prefix));
    }

    @GetMapping("/pretraga/{keyword}")
    public ResponseEntity<List<TipStudijaDTO>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(tipStudijaService.pretraziPoTipu(keyword));
    }
}