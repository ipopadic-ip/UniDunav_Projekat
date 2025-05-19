package glavniPaket.controller.korisnika;


import glavniPaket.dto.korisnika.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import glavniPaket.service.korisnika.*;

import java.util.List;

@RestController
@RequestMapping("/api/dodeljena-prava")
public class DodeljenoPravoPristupaController {

    private final DodeljenoPravoPristupaService service;

    @Autowired
    public DodeljenoPravoPristupaController(DodeljenoPravoPristupaService service) {
        this.service = service;
    }

    // Vrati sve
    @GetMapping
    public List<DodeljenoPravoPristupaDTO> findAll() {
        return service.findAll();
    }

    // Vrati po ID
    @GetMapping("/{id}")
    public DodeljenoPravoPristupaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Vrati sva prava za određenog korisnika
    @GetMapping("/korisnik/{korisnikId}")
    public List<DodeljenoPravoPristupaDTO> findByKorisnikId(@PathVariable Long korisnikId) {
        return service.findByKorisnikId(korisnikId);
    }

    // Dodaj novo pravo
    @PostMapping
    public DodeljenoPravoPristupaDTO create(@RequestBody DodeljenoPravoPristupaDTO dto) {
        return service.save(dto);
    }

    // Obriši po ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
