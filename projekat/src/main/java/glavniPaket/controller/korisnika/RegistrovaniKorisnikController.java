package glavniPaket.controller.korisnika;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import glavniPaket.dto.korisnika.RegistrovaniKorisnikDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.*;

import glavniPaket.service.korisnika.RegistrovaniKorisnikService;

@RestController
@RequestMapping("/api/registrovani-korisnici")
public class RegistrovaniKorisnikController {

    @Autowired
    private RegistrovaniKorisnikService registrovaniKorisnikService;

    public RegistrovaniKorisnikController(RegistrovaniKorisnikService registrovaniKorisnikService) {
        this.registrovaniKorisnikService = registrovaniKorisnikService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> getAll() {
        List<RegistrovaniKorisnikDTO> result = new ArrayList<>();
        for (RegistrovaniKorisnik rk : registrovaniKorisnikService.findAll()) {
            result.add(RegistrovaniKorisnikDTO.fromEntity(rk));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getOne(@PathVariable Integer id) {
        return registrovaniKorisnikService.findById(id)
                .map(k -> ResponseEntity.ok(RegistrovaniKorisnikDTO.fromEntity(k)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return registrovaniKorisnikService.findById(id)
                .map(k -> {
                    registrovaniKorisnikService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> update(@PathVariable Integer id, @RequestBody RegistrovaniKorisnikDTO dto) {
        return registrovaniKorisnikService.findById(id)
                .map(rk -> {
                    rk.setIme(dto.getIme());
                    rk.setPrezime(dto.getPrezime());
                    rk.setKorisnickoIme(dto.getKorisnickoIme());
                    rk.setDatumRodjenja(dto.getDatumRodjenja());
                    rk.setJmbg(dto.getJmbg());
                    rk.setEmail(dto.getEmail());
                    if (dto.getMestoRodjenja() != null)
                        rk.setMestoRodjenja(new Mesto(dto.getMestoRodjenja().getId(), dto.getMestoRodjenja().getNaziv(), null));
                    registrovaniKorisnikService.save(rk);
                    return ResponseEntity.ok(RegistrovaniKorisnikDTO.fromEntity(rk));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegistrovaniKorisnikDTO> create(@RequestBody RegistrovaniKorisnikDTO dto) {
        RegistrovaniKorisnik korisnik = dto.toEntity();
        korisnik = registrovaniKorisnikService.save(korisnik);
        return new ResponseEntity<>(RegistrovaniKorisnikDTO.fromEntity(korisnik), HttpStatus.CREATED);
    }
}