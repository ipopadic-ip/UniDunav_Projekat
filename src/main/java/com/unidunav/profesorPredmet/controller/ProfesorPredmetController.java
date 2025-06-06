package com.unidunav.profesorPredmet.controller;

import com.unidunav.profesorPredmet.dto.ProfesorPredmetDTO;
import com.unidunav.profesorPredmet.dto.ProfesorPredmetResponseDTO;
import com.unidunav.profesorPredmet.service.ProfesorPredmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesor-predmet")
public class ProfesorPredmetController {

    private final ProfesorPredmetService service;

    public ProfesorPredmetController(ProfesorPredmetService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROFESOR', 'SLUZBENIK', 'ADMIN')")
    public ResponseEntity<ProfesorPredmetResponseDTO> dodaj(@RequestBody ProfesorPredmetDTO dto) {
        return ResponseEntity.ok(service.dodaj(dto));
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<List<ProfesorPredmetResponseDTO>> sviZaProfesora(@PathVariable Long id) {
        return ResponseEntity.ok(service.sviZaProfesora(id));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SLUZBENIK', 'ADMIN')")
    public ResponseEntity<ProfesorPredmetResponseDTO> izmeni(@PathVariable Long id, @RequestBody ProfesorPredmetDTO dto) {
        return ResponseEntity.ok(service.izmeni(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SLUZBENIK', 'ADMIN')")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.obrisi(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
