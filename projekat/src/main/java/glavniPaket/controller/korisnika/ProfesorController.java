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

import glavniPaket.dto.korisnika.ProfesorDTO;
import glavniPaket.dto.korisnika.RegistrovaniKorisnikDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.*;
import glavniPaket.model.univerzitet.Univerzitet;
import glavniPaket.service.korisnika.ProfesorService;

@RestController
@RequestMapping("/api/profesori")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAll() {
        List<ProfesorDTO> result = new ArrayList<>();
        for (Profesor p : profesorService.findAll()) {
            result.add(new ProfesorDTO(p));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getOne(@PathVariable Long id) {
        return profesorService.findById(id)
                .map(profesor -> ResponseEntity.ok(new ProfesorDTO(profesor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return profesorService.findById(id)
                .map(p -> {
                    profesorService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> update(@PathVariable Long id, @RequestBody ProfesorDTO dto) {
        return profesorService.findById(id)
                .map(profesor -> {
                    RegistrovaniKorisnik korisnik = profesor.getKorisnik();
                    RegistrovaniKorisnikDTO korisnikDTO = dto.getKorisnik();

                    if (korisnikDTO != null && korisnik != null) {
                        korisnik.setIme(korisnikDTO.getIme());
                        korisnik.setPrezime(korisnikDTO.getPrezime());
                        korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
                        korisnik.setDatumRodjenja(korisnikDTO.getDatumRodjenja());
                        korisnik.setJmbg(korisnikDTO.getJmbg());
                        korisnik.setEmail(korisnikDTO.getEmail());
                        if (korisnikDTO.getMestoRodjenja() != null) {
                            korisnik.setMestoRodjenja(new Mesto(korisnikDTO.getMestoRodjenja().getId()));
                        }
                    }

                    profesor.setTitula(dto.getTitula());
                    profesor.setBiografija(dto.getBiografija());

                    if (dto.getUniverzitet() != null)
                        profesor.setUniverzitet(new Univerzitet(dto.getUniverzitet().getId(), null, null, null, null, null, null, profesor));
                    if (dto.getFakultet() != null)
                        profesor.setFakultet(new Fakultet(dto.getFakultet().getId(), null, null, null, profesor, null, null));
                    if (dto.getKatedra() != null)
                        profesor.setKatedra(new Katedra(dto.getKatedra().getId(), null, null, null, null, null, profesor));

                    profesorService.save(profesor);
                    return ResponseEntity.ok(new ProfesorDTO(profesor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> create(@RequestBody ProfesorDTO dto) {
        
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}

