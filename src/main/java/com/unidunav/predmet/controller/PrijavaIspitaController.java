package com.unidunav.predmet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unidunav.predmet.dto.PredmetDTO;
import com.unidunav.predmet.dto.PrijavaIspitaDTO;
import com.unidunav.predmet.service.prijavaIspita.PrijavaIspitaService;
import com.unidunav.predmet.service.prijavaIspita.PrijavaIspitaServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/prijave-ispita")
@CrossOrigin(origins = "*")
public class PrijavaIspitaController {

    @Autowired
    private PrijavaIspitaService service;

    @Autowired
    private PrijavaIspitaServiceImpl prijavaService;

    // Standardni CRUD

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO create(@RequestBody PrijavaIspitaDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PrijavaIspitaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PrijavaIspitaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO update(@PathVariable Long id, @RequestBody PrijavaIspitaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Custom funkcionalnosti za studenta

    // 1. Vrati sve predmete koje student može da prijavi
    @GetMapping("/moguce/{studentId}")
    public ResponseEntity<List<PredmetDTO>> getMogucePrijave(@PathVariable Long studentId) {
        List<PredmetDTO> predmeti = prijavaService.getPredmetiZaPrijavu(studentId);
        return ResponseEntity.ok(predmeti);
    }

    // 2. Prijavi ispit za jedan predmet
    @PostMapping("/prijavi")
    public ResponseEntity<String> prijaviIspit(@RequestBody PrijavaIspitaDTO dto) {
        prijavaService.prijavi(dto);
        return ResponseEntity.ok("Ispit uspesno prijavljen.");
    }
}
