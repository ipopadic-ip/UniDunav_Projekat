package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.dto.TrebovanjeDTO;
import com.unidunav.sluzbenik.service.TrebovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trebovanja")
@CrossOrigin("*")
public class TrebovanjeController {

    @Autowired
    private TrebovanjeService trebovanjeService;

    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<TrebovanjeDTO> create(@RequestBody TrebovanjeDTO dto) {
        return ResponseEntity.ok(trebovanjeService.create(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<List<TrebovanjeDTO>> getAll() {
        return ResponseEntity.ok(trebovanjeService.getAll());
    }
}
