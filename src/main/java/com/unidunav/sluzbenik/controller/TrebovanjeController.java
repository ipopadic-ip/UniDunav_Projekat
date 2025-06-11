package com.unidunav.sluzbenik.controller;

import com.unidunav.auth.service.JwtService;
import com.unidunav.sluzbenik.dto.TrebovanjeDTO;
import com.unidunav.sluzbenik.service.TrebovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trebovanja")
@CrossOrigin(origins = "*")
public class TrebovanjeController {

    @Autowired
    private TrebovanjeService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<TrebovanjeDTO> create(@RequestBody TrebovanjeDTO dto,
                                                @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String email = jwtService.extractEmail(jwt);
        TrebovanjeDTO saved = service.saveTrebovanje(dto, email);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<TrebovanjeDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrebovanjeDTO> update(@PathVariable Long id, @RequestBody TrebovanjeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    
    @PutMapping("/{id}/potvrdi")
    public ResponseEntity<Void> potvrdiTrebovanje(@PathVariable Long id) {
        service.potvrdiTrebovanje(id);
        return ResponseEntity.ok().build();
    }
}