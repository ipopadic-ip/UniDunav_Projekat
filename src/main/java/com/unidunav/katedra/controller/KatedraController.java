package com.unidunav.katedra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.katedra.dto.KatedraDTO;
import com.unidunav.katedra.service.KatedraService;

@RestController
@RequestMapping("/api/katedre")
@CrossOrigin(origins = "*")
public class KatedraController {

    private final KatedraService katedraService;

    public KatedraController(KatedraService katedraService) {
        this.katedraService = katedraService;
    }

    @GetMapping
    public ResponseEntity<List<KatedraDTO>> getAll() {
        return ResponseEntity.ok(katedraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KatedraDTO> getById(@PathVariable Long id) {
        KatedraDTO dto = katedraService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/departman/{departmanId}")
    public ResponseEntity<List<KatedraDTO>> getByDepartmanId(@PathVariable Long departmanId) {
        return ResponseEntity.ok(katedraService.findByDepartmanId(departmanId));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KatedraDTO> create(@RequestBody KatedraDTO dto) {
        KatedraDTO created = katedraService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KatedraDTO> update(@PathVariable Long id, @RequestBody KatedraDTO dto) {
        KatedraDTO updated = katedraService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        katedraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
