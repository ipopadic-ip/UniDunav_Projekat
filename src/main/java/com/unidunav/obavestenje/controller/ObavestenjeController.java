package com.unidunav.obavestenje.controller;

import com.unidunav.obavestenje.dto.ObavestenjeDTO;
import com.unidunav.obavestenje.dto.ObavestenjeResponseDTO;
import com.unidunav.obavestenje.model.Obavestenje;
import com.unidunav.obavestenje.service.ObavestenjeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obavestenja")
public class ObavestenjeController {

    private final ObavestenjeService obavestenjeService;

    public ObavestenjeController(ObavestenjeService obavestenjeService) {
        this.obavestenjeService = obavestenjeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROFESOR', 'SLUZBENIK')")
    public ResponseEntity<ObavestenjeResponseDTO> kreiraj(@RequestBody ObavestenjeDTO dto) {
        return ResponseEntity.ok(obavestenjeService.kreirajObavestenje(dto));
    }


    @GetMapping
    public ResponseEntity<List<Obavestenje>> svaObavestenja() {
        return ResponseEntity.ok(obavestenjeService.svaObavestenja());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obavestenje> nadjiPoId(@PathVariable Long id) {
        return ResponseEntity.ok(obavestenjeService.nadjiPoId(id));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESOR', 'SLUZBENIK')")
    public ResponseEntity<ObavestenjeResponseDTO> izmeniObavestenje(@PathVariable Long id, @RequestBody ObavestenjeDTO dto) {
        return ResponseEntity.ok(obavestenjeService.izmeniObavestenje(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESOR', 'SLUZBENIK')")
    public ResponseEntity<Void> obrisiObavestenje(@PathVariable Long id) {
        obavestenjeService.obrisiObavestenje(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/student/{id}/obavestenja")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getObavestenjaZaStudenta(@PathVariable Long id) {
        List<ObavestenjeResponseDTO> obavestenja = obavestenjeService.findObavestenjaZaStudenta(id);
        return ResponseEntity.ok(obavestenja);
    }

}