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
import com.unidunav.fakultet.dto.FakultetSimpleDTO;
import com.unidunav.fakultet.service.FakultetService;

@RestController
@RequestMapping("/api/fakulteti")
@CrossOrigin(origins = "*")
public class FakultetController {

    private final FakultetService fakultetService;

    public FakultetController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }

    @GetMapping("/original")
    public ResponseEntity<List<FakultetDTO>> findAll() {
        return ResponseEntity.ok(fakultetService.findAll());
    }
    
    // NOVI ENDPOINT za jednostavan prikaz fakulteta
    @GetMapping
    public ResponseEntity<List<FakultetSimpleDTO>> findAllSimple() {
        return ResponseEntity.ok(fakultetService.findAllSimple());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FakultetSimpleDTO> findSimpleById(@PathVariable Long id) {
        return ResponseEntity.ok(fakultetService.findSimpleById(id));
    }

    
//    @GetMapping("/{id}")
//    public ResponseEntity<FakultetSimpleDTO> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(fakultetService.findById(id));
//    }

    @GetMapping("/{id}/original")
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
