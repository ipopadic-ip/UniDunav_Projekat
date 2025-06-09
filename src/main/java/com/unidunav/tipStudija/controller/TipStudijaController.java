package com.unidunav.tipStudija.controller;

import com.unidunav.tipStudija.dto.TipStudijaDTO;
import com.unidunav.tipStudija.service.TipStudijaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipovistudija")
@CrossOrigin(origins = "*")
public class TipStudijaController {

    private final TipStudijaService tipStudijaService;

    public TipStudijaController(TipStudijaService tipStudijaService) {
        this.tipStudijaService = tipStudijaService;
    }

    @GetMapping
    public ResponseEntity<List<TipStudijaDTO>> getAll() {
        return ResponseEntity.ok(tipStudijaService.findAll());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<TipStudijaDTO>> getAllAdmin() {
        return ResponseEntity.ok(tipStudijaService.findAllAdmin());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deaktiviraj")
    public ResponseEntity<Void> deaktiviraj(@PathVariable Long id) {
        tipStudijaService.deaktiviraj(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/aktiviraj")
    public ResponseEntity<Void> aktiviraj(@PathVariable Long id) {
        tipStudijaService.aktiviraj(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipStudijaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipStudijaService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TipStudijaDTO> create(@RequestBody TipStudijaDTO dto) {
        return ResponseEntity.ok(tipStudijaService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TipStudijaDTO> update(@PathVariable Long id, @RequestBody TipStudijaDTO dto) {
        return ResponseEntity.ok(tipStudijaService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
