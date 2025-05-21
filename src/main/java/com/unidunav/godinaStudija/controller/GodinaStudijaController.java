package com.unidunav.godinaStudija.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.godinaStudija.dto.GodinaStudijaDTO;
import com.unidunav.godinaStudija.service.GodinaStudijaService;

@RestController
@RequestMapping("/api/godinestudija")
public class GodinaStudijaController {

    private final GodinaStudijaService godinaStudijaService;

    public GodinaStudijaController(GodinaStudijaService godinaStudijaService) {
        this.godinaStudijaService = godinaStudijaService;
    }

    @GetMapping
    public ResponseEntity<List<GodinaStudijaDTO>> getAll() {
        return ResponseEntity.ok(godinaStudijaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(godinaStudijaService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GodinaStudijaDTO> create(@RequestBody GodinaStudijaDTO dto) {
        return ResponseEntity.ok(godinaStudijaService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> update(@PathVariable Long id, @RequestBody GodinaStudijaDTO dto) {
        return ResponseEntity.ok(godinaStudijaService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        godinaStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
