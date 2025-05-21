package com.unidunav.profesorPredmet.controller;

import com.unidunav.profesorPredmet.dto.IshodDTO;
import com.unidunav.profesorPredmet.dto.IshodResponseDTO;
import com.unidunav.profesorPredmet.service.IshodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ishod")
public class IshodController {

    private final IshodService service;

    public IshodController(IshodService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<IshodResponseDTO> create(@RequestBody IshodDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<IshodResponseDTO> update(@PathVariable Long id, @RequestBody IshodDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public ResponseEntity<IshodResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public ResponseEntity<List<IshodResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
