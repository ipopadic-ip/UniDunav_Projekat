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

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesori() {
        return ResponseEntity.ok(profesorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> createProfesor(@RequestBody ProfesorDTO dto) {
        return new ResponseEntity<>(profesorService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> updateProfesor(@PathVariable Long id, @RequestBody ProfesorDTO dto) {
        return ResponseEntity.ok(profesorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}