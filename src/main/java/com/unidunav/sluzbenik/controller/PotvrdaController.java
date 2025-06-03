package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.dto.PotvrdaDTO;
import com.unidunav.sluzbenik.service.PotvrdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/potvrde")
@CrossOrigin(origins = "*")
public class PotvrdaController {

    @Autowired
    private PotvrdaService potvrdaService;

    // 👉 izdavanje potvrde
    @PostMapping
    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<PotvrdaDTO> izdaj(@RequestBody PotvrdaDTO dto) {
        PotvrdaDTO kreirana = potvrdaService.izdajPotvrdu(dto);
        return new ResponseEntity<>(kreirana, HttpStatus.CREATED);
    }

    // 👉 pregled svih potvrda (admin i sluzbenik)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SLUZBENIK')")
    public List<PotvrdaDTO> svePotvrde() {
        return potvrdaService.svePotvrde();
    }

    // 👉 student vidi svoje potvrde
    @GetMapping("/student/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','SLUZBENIK')")
    public List<PotvrdaDTO> potvrdeZaStudenta(@PathVariable Long id) {
        return potvrdaService.potvrdeZaStudenta(id);
    }
    
}
