package com.unidunav.fakultet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidunav.fakultet.dto.FakultetDTO;
import com.unidunav.fakultet.service.FakultetService;

@RestController
@RequestMapping("/api/fakulteti")
@CrossOrigin(origins = "*")
public class FakultetController {

    private final FakultetService fakultetService;

    public FakultetController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }

    @GetMapping
    public ResponseEntity<List<FakultetDTO>> findAll() {
        return ResponseEntity.ok(fakultetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FakultetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(fakultetService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FakultetDTO> create(@RequestBody FakultetDTO dto) {
        return ResponseEntity.ok(fakultetService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FakultetDTO> update(@PathVariable Long id, @RequestBody FakultetDTO dto) {
        return ResponseEntity.ok(fakultetService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fakultetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
