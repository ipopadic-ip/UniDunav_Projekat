package glavniPaket.controller.tipStudija;

import java.util.ArrayList;

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
import model.katedra.Katedra;

@RestController
@RequestMapping("/tip-studija")
public class TipStudijaController {

    @Autowired
    private TipStudijaService tipStudijaService;

    public TipStudijaController(TipStudijaService tipStudijaService) {
        this.tipStudijaService = tipStudijaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipStudija> getById(@PathVariable Long id) {
        return tipStudijaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<TipStudija>> getAll() {
        Iterable<TipStudija> tipoviStudija = tipStudijaService.findAll();
        return ResponseEntity.ok(tipoviStudija);
    }

    @PostMapping
    public ResponseEntity<TipStudija> create(@RequestBody TipStudija tipStudija) {
        TipStudija savedTipStudija = tipStudijaService.save(tipStudija);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipStudija);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipStudija> update(@PathVariable Long id, @RequestBody TipStudija tipStudija) {
        if (tipStudijaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tipStudija.setId(id);
        TipStudija updatedTipStudija = tipStudijaService.save(tipStudija);
        return ResponseEntity.ok(updatedTipStudija);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipStudijaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tipStudijaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TipStudijaDTO> add(@RequestBody TipStudijaDTO tipStudijaDTO) {
        TipStudija noviTipStudija = new TipStudija(null, tipStudijaDTO.getTip(), null, null);
        this.tipStudijaService.save(noviTipStudija);

        return new ResponseEntity<TipStudijaDTO>(
            new TipStudijaDTO(
                noviTipStudija.getId(),
                noviTipStudija.getTip(),
                null,
                null
            ), HttpStatus.CREATED);
    }



}

