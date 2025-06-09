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
@CrossOrigin(origins = "*")
public class ProfesorPredmetController {

    private final ProfesorPredmetService service;

    public ProfesorPredmetController(ProfesorPredmetService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<ProfesorPredmetResponseDTO>> getAktivni() {
        return ResponseEntity.ok(service.findAllAktivni());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorPredmetResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    // 2. GET - svi (admin)
    @GetMapping("/admin")
    public ResponseEntity<List<ProfesorPredmetResponseDTO>> getSvi() {
        return ResponseEntity.ok(service.findAll());
    }

    // 3. PATCH - deaktivacija
    @PatchMapping("/{id}/deaktiviraj")
    public ResponseEntity<Void> deaktiviraj(@PathVariable Long id) {
        service.setDeletedStatus(id, true);
        return ResponseEntity.noContent().build();
    }

    // 4. PATCH - aktivacija
    @PatchMapping("/{id}/aktiviraj")
    public ResponseEntity<Void> aktiviraj(@PathVariable Long id) {
        service.setDeletedStatus(id, false);
        return ResponseEntity.noContent().build();
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


}
