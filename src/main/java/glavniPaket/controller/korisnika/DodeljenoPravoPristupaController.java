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

    @Autowired
    private DodeljenoPravoPristupaService dodeljenoService;

    @PostMapping
    public ResponseEntity<DodeljenoPravoPristupaDTO> dodeliPravo(
            @RequestParam Integer korisnikId,
            @RequestParam Integer pravoPristupaId) {
        return ResponseEntity.ok(dodeljenoService.dodeliPravo(korisnikId, pravoPristupaId));
    }

    @GetMapping("/korisnik/{korisnikId}")
    public ResponseEntity<List<DodeljenoPravoPristupaDTO>> pravaZaKorisnika(@PathVariable Integer korisnikId) {
        return ResponseEntity.ok(dodeljenoService.svaPravaZaKorisnika(korisnikId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Integer id) {
        dodeljenoService.obrisiPravo(id);
        return ResponseEntity.noContent().build();
    }
}
