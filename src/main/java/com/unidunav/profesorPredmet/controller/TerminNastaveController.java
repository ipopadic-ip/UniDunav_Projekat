package com.unidunav.profesorPredmet.controller;

import com.unidunav.profesorPredmet.dto.TerminNastaveDTO;
import com.unidunav.profesorPredmet.dto.TerminNastaveResponseDTO;
import com.unidunav.profesorPredmet.service.TerminNastaveService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/termin-nastave")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('SLUZBENIK')")
public class TerminNastaveController {

    private final TerminNastaveService service;

    public TerminNastaveController(TerminNastaveService service) {
        this.service = service;
    }

    // POST - kreiranje termina
    @PostMapping
    public ResponseEntity<TerminNastaveDTO> kreiraj(@RequestBody TerminNastaveDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // GET - svi termini za datog profesora
    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<TerminNastaveDTO>> sviZaProfesora(@PathVariable Long profesorId) {
        return ResponseEntity.ok(service.findAllByProfesor(profesorId));
    }

    // PUT - ažuriranje termina
    @PutMapping("/{id}")
    public ResponseEntity<TerminNastaveDTO> azuriraj(@PathVariable Long id, @RequestBody TerminNastaveDTO dto) {
        return ResponseEntity.ok(service.azuriraj(id, dto));
    }

    // DELETE - brisanje termina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}