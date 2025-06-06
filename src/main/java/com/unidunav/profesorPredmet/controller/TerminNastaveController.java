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
public class TerminNastaveController {

    private final TerminNastaveService service;

    public TerminNastaveController(TerminNastaveService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<TerminNastaveResponseDTO> kreiraj(@RequestBody TerminNastaveDTO dto) {
        return ResponseEntity.ok(service.kreiraj(dto));
    }

    @GetMapping("/profesor-predmet/{id}")
    public ResponseEntity<List<TerminNastaveResponseDTO>> sviZaProfesorPredmet(@PathVariable Long id) {
        return ResponseEntity.ok(service.sviZaProfesorPredmet(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<TerminNastaveResponseDTO> azuriraj(@PathVariable Long id, @RequestBody TerminNastaveDTO dto) {
        return ResponseEntity.ok(service.azuriraj(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
